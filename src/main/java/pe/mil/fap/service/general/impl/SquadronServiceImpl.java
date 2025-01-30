package pe.mil.fap.service.general.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.exception.NotFoundException; 
import pe.mil.fap.entity.ImageEntity;
import pe.mil.fap.repository.SquadronRepository;
import pe.mil.fap.service.general.inf.ImageService;
import pe.mil.fap.service.general.inf.SquadronService;
import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.enums.StateEnum; 

@Service
public class SquadronServiceImpl implements SquadronService {

	@Autowired
	private SquadronRepository repo;

	@Autowired
	private ImageService imageService;

	@Override
	public Page<SquadronEntity> pageSquadrons(String filter, Pageable pageable) throws ServiceException {
		try {
			Page<SquadronEntity> pageSquadrons = repo.page(filter, pageable);
			return pageSquadrons;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	/*@Override
	public DataTableDTO page(ParameterDataTableDTO parameter) throws ServiceException {
		try {			
			Page<SquadronEntity> pageSquadron = repo.page(parameter.getFilter(), UtilHelpers.pageRequest(parameter.getStart(), parameter.getSize()));
			List<SquadronEntity> listSquadrons = pageSquadron.getContent();
		 
			
			return DataTableDTO.builder()
					.iTotalRecords((int) repo.countPageableByStateTrue())
					.iTotalDisplayRecords((int) pageSquadron.getTotalElements())
					.iDisplayRecords(listSquadrons.size())
					.aaData(listSquadrons)
					.build(); 
		} catch (Exception exception) {
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}
*/
	@Override
	public List<SquadronEntity> findAll() throws ServiceException {
		try {
			return repo.findAll().stream().filter(squadron -> squadron.getFlState().equals(StateEnum.ACTIVE.getCondition())).collect(Collectors.toList());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<SquadronEntity> findAllHistory() throws ServiceException {
		try {
			return repo.findAll();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}
	
	@Override
	public List<SquadronEntity> findByLike(SquadronEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SquadronEntity> findByID(Long id) throws ServiceException {
		try {
			return repo.findById(id);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public SquadronEntity save(SquadronEntity squadron) throws ServiceException {
		try {
			return repo.save(squadron);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public Boolean update(SquadronEntity squadron) throws ServiceException {
		try {
			repo.save(squadron);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public Boolean delete(SquadronEntity squadron) throws ServiceException {
		try {
			Optional<SquadronEntity> optSquadronFound = this.findByID(squadron.getIdSquadron());
			if (!optSquadronFound.isPresent()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_SQUADRON_NOT_FOUND);
			}
			if (!optSquadronFound.get().getFlState()) {
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_ITEM_DELETED);
			}
			optSquadronFound.get().setFlState(StateEnum.INACTIVE.getCondition());
			repo.save(optSquadronFound.get());
			return true;
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
	public SquadronEntity saveWithImage(SquadronEntity squadronEntity, MultipartFile file)  throws ServiceException{
		try {			
			if (file != null && !file.isEmpty()) {
				ImageEntity image = imageService.uploadImage(file);
				squadronEntity.setImage(image);
			}
			return repo.save(squadronEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof IOException) {
				throw new ServiceException(MessageConstants.ERROR_IN_READING_A_FILE);
			}else {
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
		
	}

	@Override
	public SquadronEntity updateWithImage(MultipartFile file, SquadronEntity squadronEntity) throws ServiceException {
		try {	
			Optional<SquadronEntity> optSquadronFound = this.findByID(squadronEntity.getIdSquadron());
			
			if (!optSquadronFound.isPresent()) { 
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_SQUADRON_NOT_FOUND);
			}
			
			if (squadronEntity.getImage() != null) {
				imageService.deleteImage(squadronEntity.getImage());
			}
			ImageEntity newImage = imageService.uploadImage(file);
			squadronEntity.setImage(newImage);
			return repo.save(squadronEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof IOException) {
				throw new ServiceException(MessageConstants.ERROR_IN_READING_A_FILE);
			}else if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			}else {
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

}
