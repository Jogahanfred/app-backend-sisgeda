package pe.mil.fap.service.general.inf;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
 
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.service.generic.GenericService;

public interface SquadronService extends GenericService<SquadronEntity> {

	Page<SquadronEntity> pageSquadrons(String filter, Pageable pageable) throws ServiceException;
	
	SquadronEntity saveWithImage(SquadronEntity squadronEntity, MultipartFile multipartFile) throws ServiceException;

	SquadronEntity updateWithImage(MultipartFile file, SquadronEntity squadron) throws ServiceException;
}
