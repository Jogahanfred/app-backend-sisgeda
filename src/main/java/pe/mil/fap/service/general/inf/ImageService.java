package pe.mil.fap.service.general.inf;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.ImageEntity;

public interface ImageService {
    ImageEntity uploadImage(MultipartFile file) throws IOException;
    void deleteImage(ImageEntity image) throws IOException;

}
