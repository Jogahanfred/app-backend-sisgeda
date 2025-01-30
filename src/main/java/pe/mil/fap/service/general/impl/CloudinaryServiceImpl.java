package pe.mil.fap.service.general.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import pe.mil.fap.service.general.inf.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	private final Cloudinary cloudinary;

	public CloudinaryServiceImpl() {
		Map<String, String> valuesMap = new HashMap<>();
		valuesMap.put("cloud_name", "jogahanfredcloud");
		valuesMap.put("api_key", "267889839558893");
		valuesMap.put("api_secret", "iQFtMzkANSlm57Aif0nbFqq-l7Y");
		cloudinary = new Cloudinary(valuesMap);
	}

	private File convert(MultipartFile multipartFile) throws IOException {
		File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		return file;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> upload(MultipartFile multipartFile) throws IOException {
		File file = convert(multipartFile);
		Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		if (!Files.deleteIfExists(file.toPath())) {
			throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> delete(String id) throws IOException {
		return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
	}

}
