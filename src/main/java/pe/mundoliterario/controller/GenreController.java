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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.mundoliterario.entity.Genre;
import pe.mundoliterario.service.GenreService;

@RestController
public class GenreController {

	@Autowired
	GenreService genreService;
	
	@GetMapping("/genre/list")
	public ResponseEntity<?> genre_list(){
		Collection<Genre> list = genreService.list();
		
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping("/genre/create")
	public ResponseEntity<?> genre_create(@RequestBody Genre genre){
		genreService.create(genre);
		Map<String, String> response = new HashMap<>();
		  response.put("message", "Género registrado correctamente.");
		  return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/genre/update/{genre_id}")
	public ResponseEntity<?> genre_update(@RequestBody Genre genre, @PathVariable Integer genre_id){
		
		Map<String, String> response = new HashMap<>();
		Genre genreDb=genreService.find_id(genre_id);
		
		if(genreDb!=null){
			
			genre.setGenre_id(genre_id);
			genreService.update(genre);
			
			response.put("message", "Género actualizado correctamente.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		response.put("message", "No se encontró el género a editar.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@GetMapping("/genre/findId/{genre_id}")
	public ResponseEntity<?> findIdGenre( @PathVariable Integer genre_id)
	{
		Map<String, String> response = new HashMap<>();
		Genre genreDb=genreService.find_id(genre_id);
		
		if(genreDb!=null){
			
			return new ResponseEntity<>(genreDb,HttpStatus.OK);
		}
		
		response.put("message", "Género no encontrado.");
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@GetMapping("/genre/findGenre/{name}")
	public ResponseEntity<?> findGenre( @PathVariable String name)
	{
		Map<String, String> response = new HashMap<>();
		Collection<Genre> genreDb=genreService.findGenre(name);
		
		if(genreDb!=null){
			
			return new ResponseEntity<>(genreDb,HttpStatus.OK);
		}
		
		response.put("message", "Género no encontrado.");
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
}
