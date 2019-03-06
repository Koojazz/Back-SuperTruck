package cap.capgemini.poe.aston.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cap.capgemini.poe.aston.entities.Product;
import cap.capgemini.poe.aston.exceptions.RessourceNotFoundException;
import cap.capgemini.poe.aston.payload.UploadFileResponse;
import cap.capgemini.poe.aston.repositories.IProductRepository;
import cap.capgemini.poe.aston.repositories.IUserRepository;
import cap.capgemini.poe.aston.services.IFileStorageService;

@CrossOrigin("*")
@RestController
public class FileController {

	@Autowired
	private IFileStorageService fileStorageService;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IProductRepository productRepository;


	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		final String fileName = this.fileStorageService.storeFile(file);

		final String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName)
				.toUriString();

		return new UploadFileResponse(fileName, fileDownloadUri,file.getContentType(),file.getSize());
	}

	@PostMapping("{productId}/uploadFile")
	public Product uploadPicture(@PathVariable(value = "productId") Long id, @RequestParam("file") MultipartFile file) {
		return this.productRepository.findById(id).map(product -> {
			final String fileName = this.fileStorageService.storeFile(file);
			final String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
			product.setImage(fileDownloadUri);
			return this.productRepository.save(product);
		}).orElseThrow(() -> new RessourceNotFoundException("product", "id", id));
	}

	@PostMapping("/uploadedMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> this.uploadFile(file)).collect(Collectors.toList());
	}

	@GetMapping("downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load a file Resource
		final Resource resource = this.fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (final IOException ex) {
			//            log.info("could not determine file type");
			ex.printStackTrace();
		}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() + "\"").body(resource);
	}
}
