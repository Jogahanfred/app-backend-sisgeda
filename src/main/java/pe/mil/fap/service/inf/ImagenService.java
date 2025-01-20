package pe.mil.fap.service.inf;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.ImagenEntity;

public interface ImagenService {
    ImagenEntity uploadImage(MultipartFile file) throws IOException;
    void deleteImage(ImagenEntity image) throws IOException;

}
