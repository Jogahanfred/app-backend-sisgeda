package pe.mil.fap.service.general.inf;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Pageable; 

import pe.mil.fap.dto.helpers.PageDTO; 
import pe.mil.fap.entity.DocumentEntity;
import pe.mil.fap.service.generic.GenericService;

public interface DocumentService extends GenericService<DocumentEntity> {
	
	PageDTO<DocumentEntity> pageDocuments(String filter, Pageable pageable) throws ServiceException;

}
