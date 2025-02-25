package pe.mil.fap.service.processes.inf;

import java.util.List; 

import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.service.exception.ServiceException;
import pe.mil.fap.service.generic.GenericService;
import pe.mil.fap.utils.enums.PersonalSituationEnum;

public interface VisitService extends GenericService<VisitEntity>{

	Boolean updateSituationVisitor(Long idVisit, Long visitor, PersonalSituationEnum coSituacion) throws ServiceException;

	List<VisitEntity> findVisitsScheduledOnTheDay() throws ServiceException;

	List<VisitEntity> findVisitsScheduledOnTheDayByNuDocument(String nuDocument) throws ServiceException;
}
