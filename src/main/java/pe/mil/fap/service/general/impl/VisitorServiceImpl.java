package pe.mil.fap.service.general.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional; 
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.dto.helpers.PageDTO;
import pe.mil.fap.entity.ImageEntity;

import pe.mil.fap.entity.VisitorEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.mappers.page.PageMapper;
import pe.mil.fap.repository.VisitorRepository;
import pe.mil.fap.service.general.inf.ImageService;
import pe.mil.fap.service.general.inf.VisitorService;
import pe.mil.fap.utils.constants.MessageConstants; 

@Service
public class VisitorServiceImpl implements VisitorService{

	@Autowired
	private VisitorRepository repo;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	@SuppressWarnings("rawtypes")
	private PageMapper pageMapper;

	@SuppressWarnings("unchecked")
	@Override
	public PageDTO<VisitorEntity> pageVisitors(String filter, Pageable pageable) throws ServiceException {
		try {
			Page<VisitorEntity> pageVisitors = repo.page(filter, pageable);
			PageDTO<VisitorEntity> page =  pageMapper.toDTO(pageVisitors);
			return page;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<VisitorEntity> findAll() throws ServiceException {
		try {
			return repo.findAll();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}
	
	@Override
	public Optional<VisitorEntity> findByDocumento(String documento) throws ServiceException {
		try {
			return repo.findByNuDocumento(documento);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<VisitorEntity> findAllHistory() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitorEntity> findByLike(VisitorEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VisitorEntity> findByID(Long id) throws ServiceException {
		try {
			return repo.findById(id);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public VisitorEntity save(VisitorEntity visitorEntity) throws ServiceException {
		try {
			return repo.save(visitorEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public Boolean update(VisitorEntity visitorEntity) throws ServiceException {
		try {
			Optional<VisitorEntity> optVisitorFound = this.findByID(visitorEntity.getIdVisitor());
			
			if (!optVisitorFound.isPresent()) { 
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND);
			}
			  
			repo.save(visitorEntity);  
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public Boolean delete(VisitorEntity t) throws ServiceException { 
		return null;
	}

	@Override
	public VisitorEntity saveWithImage(VisitorEntity visitorEntity, MultipartFile file)
			throws ServiceException {
		try {			
			if (file != null && !file.isEmpty()) {
				ImageEntity image = imageService.uploadImage(file);
				visitorEntity.setImage(image);
			}
			return repo.save(visitorEntity);
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
	public VisitorEntity updateWithImage(MultipartFile file, VisitorEntity visitorEntity) throws ServiceException {
		try {	
			Optional<VisitorEntity> optVisitorFound = this.findByID(visitorEntity.getIdVisitor());
			
			if (!optVisitorFound.isPresent()) { 
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND);
			}
			
			if (visitorEntity.getImage() != null) {
				imageService.deleteImage(visitorEntity.getImage());
			}
			ImageEntity newImage = imageService.uploadImage(file);
			visitorEntity.setImage(newImage);
			return repo.save(visitorEntity);
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
