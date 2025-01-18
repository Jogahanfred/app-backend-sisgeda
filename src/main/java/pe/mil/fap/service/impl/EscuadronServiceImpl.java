package pe.mil.fap.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.mil.fap.entity.EscuadronEntity;
import pe.mil.fap.repository.EscuadronRepository;
import pe.mil.fap.service.inf.EscuadronService;

@Service
public class EscuadronServiceImpl implements EscuadronService{
	
	 @Autowired
	 private EscuadronRepository repo;
	
	@Override
	public List<EscuadronEntity> findAll() throws Exception {
		return repo.findAll();
	}

	@Override
	public List<EscuadronEntity> findByLike(EscuadronEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EscuadronEntity> findByID(Long idd) throws Exception {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public EscuadronEntity save(EscuadronEntity t) throws Exception {
		return repo.save(t);
	}

	@Override
	public Boolean update(EscuadronEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(EscuadronEntity t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
