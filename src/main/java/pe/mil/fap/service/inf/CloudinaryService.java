package pe.mil.fap.service.inf;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	
	Map<String, Object> upload(MultipartFile multipartFile) throws IOException;
	Map<String, Object> delete(String id) throws IOException;

}
