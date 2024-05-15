/*

package pe.mundoliterario.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class ImageServiceImpl {

	

	private String mediaLocation;
	
	private Path rootLocation;
	
	

	@PostConstruct
	public void init() {
		rootLocation = Paths.get(mediaLocation);
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public String store(MultipartFile file) {
		try {	
		if(file.isEmpty()) {
			throw new RuntimeException("Fail");		
		}
		String filename = file.getOriginalFilename();
		Path destinationFile = rootLocation.resolve(Paths.get(filename)).
				normalize().toAbsolutePath();
		try(InputStream inputStream = file.getInputStream()){
			Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		}
		return filename;
		}catch(IOException e) {
			throw new RuntimeException("Fail 2", e);
		}
		
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource((file.toUri()));
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new RuntimeException("No es legible." + filename);
			}			
		}catch(MalformedURLException e) {
			throw new RuntimeException("No es legible 2." + filename);
		}
	}

}
*/
