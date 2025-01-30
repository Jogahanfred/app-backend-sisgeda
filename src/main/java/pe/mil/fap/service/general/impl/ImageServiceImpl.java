package pe.mil.fap.service.general.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.ImageEntity;
import pe.mil.fap.repository.ImageRepository;
import pe.mil.fap.service.general.inf.CloudinaryService;
import pe.mil.fap.service.general.inf.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;
    
    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }
    
	@Override
	public ImageEntity uploadImage(MultipartFile file) throws IOException {
		Map<String, Object> uploadResult = cloudinaryService.upload(file);
        String imageUrl = (String) uploadResult.get("url");
        String imageId = (String) uploadResult.get("public_id");
        ImageEntity image = new ImageEntity(file.getOriginalFilename(), imageUrl, imageId);
        return imageRepository.save(image);
	}

	@Override
	public void deleteImage(ImageEntity image) throws IOException {
        cloudinaryService.delete(image.getIdImage());
        imageRepository.deleteById(image.getId());
	}

}
