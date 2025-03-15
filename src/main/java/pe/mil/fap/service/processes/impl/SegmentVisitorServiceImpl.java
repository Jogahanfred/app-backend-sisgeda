package pe.mil.fap.service.processes.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pe.mil.fap.entity.DocumentEntity;
import pe.mil.fap.entity.ScheduleEntity;
import pe.mil.fap.entity.SegmentVisitorEntity;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.entity.VisitorEntity;
import pe.mil.fap.entity.VisitorVisitEntity;
import pe.mil.fap.entity.WorkingHoursEntity;
import pe.mil.fap.exception.BadRequestException;
import pe.mil.fap.exception.ConflictException;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.repository.SegmentVisitorRepository;
import pe.mil.fap.repository.VisitRepository;
import pe.mil.fap.service.general.inf.VisitorService;
import pe.mil.fap.service.processes.inf.SegmentVisitorService;
import pe.mil.fap.service.processes.inf.VisitService;
import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.enums.DayOfWeekEnum;
import pe.mil.fap.utils.enums.PersonalSituationEnum;
import pe.mil.fap.utils.enums.SegmentTypeEnum;
import pe.mil.fap.utils.functions.UtilHelpers;

@Service
public class SegmentVisitorServiceImpl implements SegmentVisitorService{

	@Autowired
	private SegmentVisitorRepository repo;

	@Autowired
	private VisitRepository visitRepo;
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private VisitorService visitorService;
	
	@Override
	public List<SegmentVisitorEntity> findAllForEveryDay(String day) throws ServiceException {
		try {
	        LocalDate date = LocalDate.parse(day);
			return repo.findAll().stream().filter(segment -> segment.getFeStartSegment().toLocalDate().equals(date)).collect(Collectors.toList());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<SegmentVisitorEntity> findAllOfToday() throws ServiceException {
		try {
			return repo.findAll().stream().filter(segment -> segment.getFeStartSegment().toLocalDate().equals(LocalDate.now())).collect(Collectors.toList());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<SegmentVisitorEntity> findAll() throws ServiceException {
		try {
			return repo.findAll();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<SegmentVisitorEntity> findAllHistory() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SegmentVisitorEntity> findByLike(SegmentVisitorEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SegmentVisitorEntity> findByID(Long id) throws ServiceException {
		try {
			return repo.findById(id);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}
	
	@Override
	public SegmentVisitorEntity save(SegmentVisitorEntity segmentVisitor) throws ServiceException {
		return null;
	}

	@Transactional
	@Override
	public SegmentVisitorEntity entranceSegmentVisitor(Long idVisit, Long idVisitor) throws ServiceException {
		try { 
			
			if (repo.validateVisitorEntry(idVisit, idVisitor, UtilHelpers.getCurrentDateFormatted()) == 1 ? true : false) {
				throw new ConflictException(MessageConstants.SUCCESS_MESSAGE_VISIT_ENTRANCE_DUPLICATE);
			}

			Optional<VisitEntity> optVisitFound = visitService.findByID(idVisit);
			
			if (!optVisitFound.get().getDocument().isFeExpirationValid()) {
				throw new BadRequestException(MessageConstants.INFO_DOCUMENT_DATE_EXPIRED);
			}			
			
			if (optVisitFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_NOT_FOUND);
			}
			
			if (!optVisitFound.get().isDateWithinRange(LocalDate.now())) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_RANGE_INVALID);				
			}
			
			Boolean existsVisitor = optVisitFound.get().getVisitorVisit().stream().anyMatch(visitorVisit -> visitorVisit.getVisitor().getIdVisitor().equals(idVisitor));
			
			if (!existsVisitor) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND);				
			}

			Boolean visitorPermitted = !optVisitFound.get().getVisitorVisit().stream()
				    .filter(visitorVisit -> visitorVisit.getVisitor().getIdVisitor().equals(idVisitor))
				    .anyMatch(visitorVisit -> !visitorVisit.getCoSituation().equals(PersonalSituationEnum.PERMITTED));
 
			if (visitorPermitted) {
				DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
			    LocalTime currentTime = LocalTime.now(); 
			    
			    Optional<WorkingHoursEntity> workingHourForToday = optVisitFound.get()
		                .getSchedule().getWorkingHours().stream()
		                .filter(workingHour -> workingHour.getNoDay().equals(DayOfWeekEnum.valueOf(currentDay.name()))) 
		                .findFirst();  
				
			    if (workingHourForToday.isPresent()) {
		            WorkingHoursEntity workingHour = workingHourForToday.get();
		            if (currentTime.isBefore(workingHour.getFeStartWork()) || (workingHour.getFeEndWork() != null && currentTime.isAfter(workingHour.getFeEndWork()))) {
		                throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_NOT_AUTHORIZED_TIME);
		            }
		        } else {
		            throw new NotFoundException(MessageConstants.INFO_MESSAGE_NO_WORKING_HOURS_FOR_TODAY);
		        }

			    VisitorEntity visitor = new VisitorEntity();
			    visitor.setIdVisitor(idVisitor);
			    
			    SegmentVisitorEntity segment = new SegmentVisitorEntity();
			    segment.setSegment(optVisitFound.get());
			    segment.setVisitor(visitor);
			    segment.setCoSegmentType(SegmentTypeEnum.ENTRANCE);
			    segment.setFeStartSegment(LocalDateTime.now());
			    return repo.save(segment);	 	

			}else { 
				Optional<PersonalSituationEnum> visitorSituation = optVisitFound.get().getVisitorVisit().stream()
					    .filter(visitorVisit -> visitorVisit.getVisitor().getIdVisitor().equals(idVisitor))
					    .map(visitorVisit -> visitorVisit.getCoSituation()) 
					    .findFirst(); 
	  
				VisitorEntity visitor = new VisitorEntity();
				visitor.setIdVisitor(idVisitor);
					    
				SegmentVisitorEntity segment = new SegmentVisitorEntity();
				segment.setSegment(optVisitFound.get());
				segment.setVisitor(visitor);
				segment.setCoSegmentType(SegmentTypeEnum.DETENTION);
				segment.setFeStartSegment(LocalDateTime.now());
				segment.setFeEndSegment(LocalDateTime.now());
				segment.setTxSegmentDetail(MessageConstants.INFO_MESSAGE_VISITOR_DETENTION + " ("+visitorSituation.get().name() + ")");
				return repo.save(segment);
			}   
		    
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else if(exception instanceof ConflictException) {
				throw new ConflictException(exception.getMessage());
			}else if(exception instanceof BadRequestException) {
				throw new BadRequestException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Transactional
	@Override
	public SegmentVisitorEntity exitSegmentVisitor(Long idSegment) throws ServiceException {
		try { 

			Optional<SegmentVisitorEntity> optSegmentFound = this.findByID(idSegment);
			
			if (optSegmentFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_SEGMENT_VISIT_NOT_FOUND);
			}
			
			if (repo.validateVisitorExit(idSegment, UtilHelpers.getCurrentDateFormatted()) == 1 ? true : false) {
				throw new ConflictException(MessageConstants.SUCCESS_MESSAGE_VISIT_EXIT_DUPLICATE);
			}
			 
			repo.updateSituationVisitorExit(idSegment);
		    return this.findByID(idSegment).get();
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else if(exception instanceof ConflictException) {
				throw new ConflictException(exception.getMessage());
			}else if(exception instanceof BadRequestException) {
				throw new BadRequestException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}
/*	
	@Transactional
	@Override
	public SegmentVisitorEntity detentionSegmentVisitor(Long idVisit, Long idVisitor) throws ServiceException {
		try { 
			
			if (repo.validateVisitorEntry(idVisit, idVisitor, UtilHelpers.getCurrentDateFormatted()) == 1 ? true : false) {
				throw new ConflictException(MessageConstants.SUCCESS_MESSAGE_VISIT_ENTRANCE_DUPLICATE);
			}

			Optional<VisitEntity> optVisitFound = visitService.findByID(idVisit);
			
			if (optVisitFound.isEmpty()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_NOT_FOUND);
			}
			
			if (!optVisitFound.get().isDateWithinRange(LocalDate.now())) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_RANGE_INVALID);				
			}
			
			Boolean existsVisitor = optVisitFound.get().getVisitorVisit().stream().anyMatch(visitorVisit -> visitorVisit.getVisitor().getIdVisitor().equals(idVisitor));
			
			if (!existsVisitor) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND);				
			}

			Optional<PersonalSituationEnum> visitorSituation = optVisitFound.get().getVisitorVisit().stream()
				    .filter(visitorVisit -> visitorVisit.getVisitor().getIdVisitor().equals(idVisitor))
				    .map(visitorVisit -> visitorVisit.getCoSituation()) 
				    .findFirst(); 
  
			VisitorEntity visitor = new VisitorEntity();
			visitor.setIdVisitor(idVisitor);
				    
			SegmentVisitorEntity segment = new SegmentVisitorEntity();
			segment.setSegment(optVisitFound.get());
			segment.setVisitor(visitor);
			segment.setCoSegmentType(SegmentTypeEnum.DETENTION);
			segment.setFeStartSegment(LocalDateTime.now());
			segment.setTxSegmentDetail(MessageConstants.INFO_MESSAGE_VISITOR_DETENTION + " ("+visitorSituation.get().name() + ")");
			return repo.save(segment);	   
  
		    
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else if(exception instanceof ConflictException) {
				throw new ConflictException(exception.getMessage());
			}else if(exception instanceof BadRequestException) {
				throw new BadRequestException(exception.getMessage());
			}else {				
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}
*/
	@Override
	public Boolean update(SegmentVisitorEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(SegmentVisitorEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
