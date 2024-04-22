package pe.mundoliterario.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.mundoliterario.entity.Genre;
import pe.mundoliterario.entity.Subgenre;
import pe.mundoliterario.service.GenreService;
import pe.mundoliterario.service.SubgenreService;
import pe.mundoliterario.vo.GenreSubgenre;

@RestController
public class GenreSubgenreController {
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private SubgenreService subgenreService;
	
	public GenreSubgenreController() {
	}
	
	@GetMapping("/gensubgenre/listId")
	public ResponseEntity<?> listarGenreSubId(){
		return new ResponseEntity<>(genreService.listGenreSubId(),HttpStatus.OK);
	}
	
	@GetMapping("/gensubgenre/list")
	public ResponseEntity<?> listarGenreSub(){
		return new ResponseEntity<>(genreService.listGenreSub(),HttpStatus.OK);
	}

	@PostMapping("/gensubgenre/create")
	public ResponseEntity<?> genresubgenre_create(@RequestBody GenreSubgenre genreSubgenre)
	{
		Map<String, String> response = new HashMap<>();
		Integer subgenreId=genreSubgenre.getSubgenre().getSubgenre_id();
		Subgenre subgenreDb=subgenreService.find_id(subgenreId);
		
		if(subgenreDb!=null)
		{						
			Integer genreId=genreSubgenre.getGenre().getGenre_id();
			Genre genreDb=genreService.find_id(genreId);
			
			if(genreDb!=null)
			{
				genreDb.addSubgenre(subgenreDb);
				genreService.update(genreDb);
				response.put("message", "¡Genero-subgenero CREADO!");
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}
			
			response.put("message", "No existe el género");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);		
		}
		
		response.put("message", "No existe el subgénero");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);		
	}
	
	@GetMapping("/gensubgenre/findUnassignedSubgenresByGenreId/{genre_id}")
	public ResponseEntity<?> findUnassignedSubgenresByGenreId( @PathVariable Integer genre_id)
	{
		Map<String, String> response = new HashMap<>();
		Collection<Subgenre> unnassignedSubgenres =subgenreService.findUnassignedSubgenresByGenreId(genre_id);
		
		if(unnassignedSubgenres!=null){		
			
			return new ResponseEntity<>(unnassignedSubgenres,HttpStatus.OK);
		}
		
		response.put("message", "No se encontraron subgéneros sin asignar");
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
