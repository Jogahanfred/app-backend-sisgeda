package pe.mil.fap.service.processes.impl;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.mil.fap.dto.helpers.DailyVisitStatusDTO;
import pe.mil.fap.dto.helpers.VisitScheduleByVisitorDTO;
import pe.mil.fap.dto.helpers.VisitorScheduleOnTheDayDTO;
import pe.mil.fap.entity.DocumentEntity;
import pe.mil.fap.entity.ScheduleEntity;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.entity.VisitorEntity;
import pe.mil.fap.entity.VisitorVisitEntity;
import pe.mil.fap.exception.BadRequestException;
import pe.mil.fap.exception.ConflictException;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.repository.VisitRepository;
import pe.mil.fap.service.general.inf.DocumentService;
import pe.mil.fap.service.general.inf.SquadronService;
import pe.mil.fap.service.general.inf.VisitorService;
import pe.mil.fap.service.processes.inf.ScheduleService;
import pe.mil.fap.service.processes.inf.VisitService;
import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.enums.PersonalSituationEnum;
import pe.mil.fap.utils.enums.SegmentTypeEnum;
import pe.mil.fap.utils.enums.VisitSituationEnum; 

@Service
public class VisitServiceImpl implements VisitService{

	@Autowired
	private VisitRepository repo; 
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private SquadronService squadronService;
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Override
	public List<VisitEntity> findAll() throws ServiceException {
		try {
			return repo.findAll().stream().map(visit -> {
				if (visit.getFeEnd().isBefore(LocalDate.now())) {
					if (this.findSegmentVisit(visit.getIdVisit())) {
						visit.setNoVisitSituation(VisitSituationEnum.COMPLETED);	
					} else {
						visit.setNoVisitSituation(VisitSituationEnum.EXPIRED);
					}
				}else if (visit.getFeStart().isAfter(LocalDate.now())) {
					visit.setNoVisitSituation(VisitSituationEnum.PENDING);
				}else if (!LocalDate.now().isBefore(visit.getFeStart()) && !LocalDate.now().isAfter(visit.getFeEnd())) {
					if (this.findSegmentVisit(visit.getIdVisit())) {
						if (this.findSegmentVisitTypeEntrance(visit.getIdVisit())) {
							visit.setNoVisitSituation(VisitSituationEnum.IN_PROGRESS);	
						}else {							
							visit.setNoVisitSituation(VisitSituationEnum.STARTED);	
						}
					}else{
						visit.setNoVisitSituation(VisitSituationEnum.SCHEDULED);
					} 
				}
				return visit;
				
			}).collect(Collectors.toList());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<VisitEntity> findAllHistory() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitEntity> findByLike(VisitEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VisitEntity> findByID(Long id) throws ServiceException {
		try {
			return repo.findById(id);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Transactional
	@Override
	public VisitEntity save(VisitEntity visitEntity) throws ServiceException {
		try { 
			if (!visitEntity.isFeStartValid()) {
				throw new BadRequestException(MessageConstants.INFO_INVALID_START_DATE);
		    }	
			
			Optional<ScheduleEntity> optScheduleFound = scheduleService.findByID(visitEntity.getSchedule().getIdSchedule());
			
			if (optScheduleFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_SCHEDULE_NOT_FOUND);
			}
			
			Optional<DocumentEntity> optDocumentFound = documentService.findByID(visitEntity.getDocument().getIdDocument());
			
			if (optDocumentFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_DOCUMENT_NOT_FOUND);
			}
			
			if (!optDocumentFound.get().isFeExpirationValid()) {
				throw new BadRequestException(MessageConstants.INFO_DOCUMENT_DATE_EXPIRED);
			}
			
			Optional<SquadronEntity> optSquadronFound = squadronService.findByID(visitEntity.getSquadron().getIdSquadron());
			
			if (optSquadronFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_SQUADRON_NOT_FOUND);
			}
			
			// VALIDATE DUPLICATE VISITOR
			Set<Long> setIdVisitors = visitEntity.getVisitorVisit()
												  .stream()
												  .map(visitor -> visitor.getVisitor().getIdVisitor())
												  .collect(Collectors.toSet());
			
			if (setIdVisitors.size() != visitEntity.getVisitorVisit().size()) {
				throw new BadRequestException(MessageConstants.INFO_DUPLICATE_VISITOR_ID);
			} 
			
			for (Long idVisitor : setIdVisitors) {
				Optional<VisitorEntity> optVisitorFound = visitorService.findByID(idVisitor);
				if (optVisitorFound.isEmpty()) {
					throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND + " (ID: " + idVisitor + ")");		
				}
				if (this.findScheduledVisitToVisitor(idVisitor)) {
					throw new ConflictException(MessageConstants.INFO_VISITOR_HAS_VISIT_IN_PROGRESS + " (Visitante: " + optVisitorFound.get().getNoSurName() + ")");					
				}
			} 			
			
			visitEntity.getVisitorVisit().stream().forEach(visitorVisit -> {				
				visitorVisit.setVisit(visitEntity);
				visitorVisit.setCoSituation(PersonalSituationEnum.PERMITTED);
			});
			
			return repo.save(visitEntity);				
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else if(exception instanceof BadRequestException) {
				throw new BadRequestException(exception.getMessage());
			}else if(exception instanceof ConflictException) {
				throw new ConflictException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Override
	public Boolean update(VisitEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(VisitEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VisitEntity> updateSituationVisitor(Long idVisit, Long idVisitor, PersonalSituationEnum coSituation) throws ServiceException {
		try {
			Optional<VisitorEntity> optVisitorFound = visitorService.findByID(idVisitor);
			if (optVisitorFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND + " (ID: " + idVisitor + ")");		
			}
			
			Optional<VisitEntity> optVisitFound = this.findByID(idVisit);
			if (optVisitFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_NOT_FOUND + " (ID: " + idVisitor + ")");		
			}
			
			repo.updateSituationVisitor(idVisit, idVisitor, coSituation);
			return this.findByID(idVisit);
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Override
	public List<VisitEntity> findVisitsScheduledOnTheDay() throws ServiceException{
		try { 
			return repo.findVisitsScheduledOnTheDay().stream().map(visit -> {
				if (visit.getFeEnd().isBefore(LocalDate.now())) {
					if (this.findSegmentVisit(visit.getIdVisit())) {
						visit.setNoVisitSituation(VisitSituationEnum.COMPLETED);	
					} else {
						visit.setNoVisitSituation(VisitSituationEnum.EXPIRED);
					}
				}else if (visit.getFeStart().isAfter(LocalDate.now())) {
					visit.setNoVisitSituation(VisitSituationEnum.PENDING);
				}else if (!LocalDate.now().isBefore(visit.getFeStart()) && !LocalDate.now().isAfter(visit.getFeEnd())) {
					if (this.findSegmentVisit(visit.getIdVisit())) {
						if (this.findSegmentVisitTypeEntrance(visit.getIdVisit())) {
							visit.setNoVisitSituation(VisitSituationEnum.IN_PROGRESS);	
						}else {							
							visit.setNoVisitSituation(VisitSituationEnum.STARTED);	
						}
					}else{
						visit.setNoVisitSituation(VisitSituationEnum.SCHEDULED);
					} 
				}
				return visit;
				
			}).collect(Collectors.toList());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<VisitorScheduleOnTheDayDTO> findVisitorsScheduledOnTheDay() throws ServiceException {
		try {
			return repo.findVisitorsScheduledOnTheDay();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public VisitScheduleByVisitorDTO findVisitsScheduledOnTheDayByNuDocument(String nuDocument) throws ServiceException{
		try {
			Optional<VisitorEntity> optVisitor = this.visitorService.findByDocumento(nuDocument);
			if (optVisitor.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND);
			}
			List<VisitEntity> lstVisit = repo.findAll();
			LocalDate today = LocalDate.now();
			
			List<VisitEntity> filteredVisit = lstVisit.stream().filter(visit -> {
		        boolean isWithinDateRange = !today.isBefore(visit.getFeStart()) && !today.isAfter(visit.getFeEnd());
		        boolean isDocumentMatched = visit.getVisitorVisit().stream().anyMatch(visitorVisit  -> visitorVisit .getVisitor().getNuDocument().equals(nuDocument));
		        return isWithinDateRange && isDocumentMatched;
			}).collect(Collectors.toList());
 
			
			Boolean isNotPermitted = filteredVisit.stream()
												  .anyMatch(visit -> 
												  			visit.getVisitorVisit()
												  				 .stream()
																 .filter(visitorVisit -> visitorVisit.getVisitor().getNuDocument().equals(nuDocument))
												  				 .anyMatch(visitorVisit -> 
												  					!visitorVisit.getCoSituation().equals(PersonalSituationEnum.PERMITTED)
												  				  )
											                );
			
			VisitScheduleByVisitorDTO visitSchedule = new VisitScheduleByVisitorDTO();
			visitSchedule.setVisitor(optVisitor.get());
			visitSchedule.setCoSituationVisitor(isNotPermitted ? SegmentTypeEnum.DETENTION : SegmentTypeEnum.ENTRANCE);
			visitSchedule.setVisits(filteredVisit);
			return visitSchedule;
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Override
	public Boolean findScheduledVisitToVisitor(Long idVisitor) throws ServiceException {
		try {
			Optional<VisitorEntity> optVisitor = this.visitorService.findByID(idVisitor);
			if (optVisitor.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND);				
			}
			return repo.findScheduledVisitToVisitor(idVisitor) > 0 ? true : false;
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Override
	public Boolean findSegmentVisit(Long idVisit) throws ServiceException {
		try {
			Optional<VisitEntity> optVisit = this.findByID(idVisit);
			if (optVisit.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_NOT_FOUND);				
			}
			return repo.findSegmentVisit(idVisit) > 0 ? true : false;
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Override
	public Boolean findSegmentVisitTypeEntrance(Long idVisit) throws ServiceException {
		try {
			Optional<VisitEntity> optVisit = this.findByID(idVisit);
			if (optVisit.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_NOT_FOUND);				
			}
			return repo.findSegmentVisitTypeEntrance(idVisit) > 0 ? true : false;
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Override
	public DailyVisitStatusDTO findDailyVisitStatus() throws ServiceException {
		try { 
			DailyVisitStatusDTO daily = new DailyVisitStatusDTO();
			daily.setTotalScheduledVisits(this.findVisitsScheduledOnTheDay().size());
			daily.setTotalScheduledVisitors(repo.counterVisitorsScheduledOnTheDay());
			daily.setTotalVisitorsRegisteredInProgress(repo.counterVisitorsScheduledOnTheDayInProgress());
			daily.setTotalVisitorsRegisteredExit(repo.counterVisitorsScheduledOnTheDayExit());
			daily.setTotalVisitorsRegisteredDenied(repo.counterVisitorsScheduledOnTheDayDetention());
			return daily;
		} catch (Exception exception) {
			exception.printStackTrace(); 			
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}
	
	
}
