package pe.mil.fap.service.processes.inf;

import java.time.LocalDate;
import java.util.List;

import pe.mil.fap.entity.SegmentVisitorEntity;
import pe.mil.fap.service.exception.ServiceException;
import pe.mil.fap.service.generic.GenericService;

public interface SegmentVisitorService extends GenericService<SegmentVisitorEntity> {

	List<SegmentVisitorEntity> findAllOfToday() throws ServiceException;
	List<SegmentVisitorEntity> findAllForEveryDay(String day) throws ServiceException;
	SegmentVisitorEntity entranceSegmentVisitor(Long idVisit, Long idVisitor) throws ServiceException; 
	SegmentVisitorEntity exitSegmentVisitor(Long idSegment) throws ServiceException; 
	/*SegmentVisitorEntity detentionSegmentVisitor(Long idVisit, Long idVisitor) throws ServiceException; */
}
