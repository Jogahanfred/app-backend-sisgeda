package pe.mil.fap.service.general.impl;
 
import java.util.List;
import java.util.Optional; 
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service; 

import pe.mil.fap.dto.helpers.PageDTO; 

import pe.mil.fap.entity.DocumentEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.mappers.page.PageMapper;
import pe.mil.fap.repository.DocumentRepository; 
import pe.mil.fap.service.general.inf.DocumentService;
import pe.mil.fap.utils.constants.MessageConstants; 

@Service
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	private DocumentRepository repo; 
	
	@Autowired
	@SuppressWarnings("rawtypes")
	private PageMapper pageMapper;

	@SuppressWarnings("unchecked")
	@Override
	public PageDTO<DocumentEntity> pageDocuments(String filter, Pageable pageable) throws ServiceException {
		try {
			Page<DocumentEntity> pageDocuments = repo.page(filter, pageable);
			PageDTO<DocumentEntity> page =  pageMapper.toDTO(pageDocuments);
			return page;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<DocumentEntity> findAll() throws ServiceException {
		try {
			return repo.findAll();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<DocumentEntity> findAllHistory() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentEntity> findByLike(DocumentEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DocumentEntity> findByID(Long id) throws ServiceException {
		try {
			return repo.findById(id);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public DocumentEntity save(DocumentEntity documentEntity) throws ServiceException {
		try {
			return repo.save(documentEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public Boolean update(DocumentEntity documentEntity) throws ServiceException {
		try {
			Optional<DocumentEntity> optDocumentFound = this.findByID(documentEntity.getIdDocument());
			
			if (!optDocumentFound.isPresent()) { 
				throw new NotFoundException(MessageConstants.INFO_MESSAGE_DOCUMENT_NOT_FOUND);
			}
			  
			repo.save(documentEntity);  
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public Boolean delete(DocumentEntity t) throws ServiceException { 
		return null;
	}

}
