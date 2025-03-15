package pe.mil.fap.service.processes.inf;

import java.util.List;
import java.util.Optional;

import pe.mil.fap.dto.helpers.DailyVisitStatusDTO;
import pe.mil.fap.dto.helpers.VisitScheduleByVisitorDTO;
import pe.mil.fap.dto.helpers.VisitorScheduleOnTheDayDTO;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.service.exception.ServiceException;
import pe.mil.fap.service.generic.GenericService;
import pe.mil.fap.utils.enums.PersonalSituationEnum;

public interface VisitService extends GenericService<VisitEntity> {

	Optional<VisitEntity> updateSituationVisitor(Long idVisit, Long visitor, PersonalSituationEnum coSituacion)
			throws ServiceException;

	List<VisitEntity> findVisitsScheduledOnTheDay() throws ServiceException;

	List<VisitorScheduleOnTheDayDTO> findVisitorsScheduledOnTheDay() throws ServiceException;

	VisitScheduleByVisitorDTO findVisitsScheduledOnTheDayByNuDocument(String nuDocument) throws ServiceException;

	Boolean findScheduledVisitToVisitor(Long idVisitor) throws ServiceException;

	Boolean findSegmentVisit(Long idVisit) throws ServiceException;

	Boolean findSegmentVisitTypeEntrance(Long idVisit) throws ServiceException;
	
	DailyVisitStatusDTO findDailyVisitStatus() throws ServiceException;
 
}
