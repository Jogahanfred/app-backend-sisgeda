package pe.mil.fap.service.processes.inf;

import pe.mil.fap.entity.SegmentVisitorEntity;
import pe.mil.fap.service.exception.ServiceException;
import pe.mil.fap.service.generic.GenericService;

public interface SegmentVisitorService extends GenericService<SegmentVisitorEntity> {

	SegmentVisitorEntity entranceSegmentVisitor(Long idVisit, Long idVisitor) throws ServiceException; 
	SegmentVisitorEntity exitSegmentVisitor(Long idSegment) throws ServiceException; 
	/*SegmentVisitorEntity detentionSegmentVisitor(Long idVisit, Long idVisitor) throws ServiceException; */
}
