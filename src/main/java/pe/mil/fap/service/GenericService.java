package pe.mil.fap.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

	List<T> findAll() throws Exception;
	
	List<T> findByLike(T t) throws Exception;

	Optional<T> findByID(Long idd) throws Exception;

	T save(T t) throws Exception;

	Boolean update(T t) throws Exception;

	Boolean delete(T t) throws Exception;
}
