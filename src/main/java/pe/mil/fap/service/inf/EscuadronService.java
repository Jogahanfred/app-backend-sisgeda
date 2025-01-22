package pe.mil.fap.service.inf;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.EscuadronEntity;
import pe.mil.fap.service.GenericService;

public interface EscuadronService extends GenericService<EscuadronEntity> {
	
	EscuadronEntity saveWithImage (EscuadronEntity escuadronEntity, MultipartFile multipartFile) throws IOException;

	EscuadronEntity updateWithImage(MultipartFile file, EscuadronEntity escuadron) throws IOException;
}
