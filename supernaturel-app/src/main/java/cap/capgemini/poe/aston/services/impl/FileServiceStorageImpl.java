package cap.capgemini.poe.aston.services.impl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import cap.capgemini.poe.aston.exceptions.FileStorageException;
import cap.capgemini.poe.aston.exceptions.MyFileNotFoundException;
import cap.capgemini.poe.aston.properties.FileStorageProperties;
import cap.capgemini.poe.aston.services.IFileStorageService;

@Service
@Transactional
public class FileServiceStorageImpl implements IFileStorageService {

private final Path fileStorageLocation;
	
	@Autowired
	public FileServiceStorageImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (final Exception e) {
			throw new FileStorageException("impossible de créer le repertoire à l'endroit souhaiter pour stocker le fichier uploadé");
		}
	}

	@Override
	public String storeFile(MultipartFile file) {
		//Normalize file name
        final String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence" + fileName);
            }

            final Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (final Exception ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again", ex);
        }
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		
		try {
            final Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            final Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (final MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
	}

}
