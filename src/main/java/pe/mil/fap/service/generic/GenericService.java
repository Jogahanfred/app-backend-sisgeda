package pe.mil.fap.service.generic;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;

public interface GenericService<T> {

	List<T> findAll() throws ServiceException;

	List<T> findAllHistory() throws ServiceException;
	
	List<T> findByLike(T t) throws ServiceException;

	Optional<T> findByID(Long id) throws ServiceException;

	T save(T t) throws ServiceException;

	Boolean update(T t) throws ServiceException;

	Boolean delete(T t) throws ServiceException;
}
