package pe.mil.fap.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.EscuadronEntity;
import pe.mil.fap.entity.ImagenEntity;
import pe.mil.fap.repository.EscuadronRepository;
import pe.mil.fap.service.inf.EscuadronService;
import pe.mil.fap.service.inf.ImagenService;

@Service
public class EscuadronServiceImpl implements EscuadronService{
	
	 @Autowired
	 private EscuadronRepository repo;
	 
	 @Autowired
	 private ImagenService imagenService;
	
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

	@Override
	public EscuadronEntity saveWithImage(EscuadronEntity escuadronEntity, MultipartFile file) throws IOException {
		 if (file != null && !file.isEmpty()){
	            ImagenEntity image = imagenService.uploadImage(file);
	            escuadronEntity.setImagen(image);
	        }
	        return repo.save(escuadronEntity);
	}

	@Override
	public EscuadronEntity updateWithImage(MultipartFile file, EscuadronEntity escuadron) throws IOException {
		  if (escuadron.getImagen() != null) {
	            imagenService.deleteImage(escuadron.getImagen());
	        }
	        ImagenEntity newImage = imagenService.uploadImage(file);
	        escuadron.setImagen(newImage);
	        return repo.save(escuadron);
	}

}
