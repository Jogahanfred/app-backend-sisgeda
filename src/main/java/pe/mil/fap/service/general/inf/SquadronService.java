package pe.mil.fap.service.general.inf;

import org.hibernate.service.spi.ServiceException; 
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.dto.helpers.PageDTO;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.service.generic.GenericService;

public interface SquadronService extends GenericService<SquadronEntity> {

	PageDTO<SquadronEntity> pageSquadrons(String filter, Pageable pageable) throws ServiceException;
	
	SquadronEntity saveWithImage(SquadronEntity squadronEntity, MultipartFile multipartFile) throws ServiceException;

	SquadronEntity updateWithImage(MultipartFile file, SquadronEntity squadron) throws ServiceException;
}
