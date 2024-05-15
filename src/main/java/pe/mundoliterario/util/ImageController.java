/*
package pe.mundoliterario.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
public class ImageController {
	
	private ImageService imageService;
	
	private HttpServletRequest request;

	/*
	@PostMapping("upload")
	public Map<String, String> uploadFile(@RequestParam("file") MultipartFile multipartFile){
		String path = imageService.store(multipartFile);
		String host = request.getRequestURI().toString().replace(request.getRequestURI(),"");
		String url = ServletUriComponentsBuilder
				.fromHttpUrl(host)
				.path("/media/")
				.path(path)
				.toString();
		return Map.Entry
	}*/
