package pe.mil.fap.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.ImagenEntity;
import pe.mil.fap.repository.ImagenRepository;
import pe.mil.fap.service.inf.CloudinaryService;
import pe.mil.fap.service.inf.ImagenService;

@Service
public class ImagenServiceImpl implements ImagenService {

    private final CloudinaryService cloudinaryService;
    private final ImagenRepository imageRepository;
    
    public ImagenServiceImpl(CloudinaryService cloudinaryService, ImagenRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }
    
	@Override
	public ImagenEntity uploadImage(MultipartFile file) throws IOException {
		Map uploadResult = cloudinaryService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        String imageId = (String) uploadResult.get("public_id");
        ImagenEntity image = new ImagenEntity(file.getOriginalFilename(), imageUrl, imageId);
        return imageRepository.save(image);
	}

	@Override
	public void deleteImage(ImagenEntity image) throws IOException {

        cloudinaryService.delete(image.getIdImagen());
        imageRepository.deleteById(image.getId());
		
	}

}
