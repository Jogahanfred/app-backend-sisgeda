package pe.mil.fap.service.general.inf;

import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.dto.helpers.PageDTO;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.entity.VisitorEntity;
import pe.mil.fap.service.generic.GenericService;

public interface VisitorService extends GenericService<VisitorEntity> {
	
	PageDTO<VisitorEntity> pageVisitors(String filter, Pageable pageable) throws ServiceException;
	
	Optional<VisitorEntity> findByDocumento(String documento) throws ServiceException;
	
	VisitorEntity saveWithImage(VisitorEntity visitorEntity, MultipartFile multipartFile) throws ServiceException;

	VisitorEntity updateWithImage(MultipartFile file, VisitorEntity visitorEntity) throws ServiceException;

}
