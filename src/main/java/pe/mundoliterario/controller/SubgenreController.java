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
import pe.mundoliterario.entity.Subgenre;
import pe.mundoliterario.service.SubgenreService;

@RestController
public class SubgenreController {
	
	@Autowired
	SubgenreService subgenreService;
	
	@GetMapping("/subgenre/list")
	public ResponseEntity<?> subgenre_list(){
		Collection<Subgenre> list = subgenreService.list();
		
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping("/subgenre/create")
	public ResponseEntity<?> subgenre_create(@RequestBody Subgenre subgenre){
		subgenreService.create(subgenre);
		Map<String, String> response = new HashMap<>();
		  response.put("message", "Subgénero registrado correctamente.");
		  return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/subgenre/update/{subgenre_id}")
	public ResponseEntity<?> subgenre_update(@RequestBody Subgenre subgenre, @PathVariable Integer subgenre_id){
		
		Map<String, String> response = new HashMap<>();
		Subgenre subgenreDb=subgenreService.find_id(subgenre_id);
		
		if(subgenreDb!=null){
			
			subgenre.setSubgenre_id(subgenre_id);
			subgenreService.update(subgenre);
			
			response.put("message", "Subgénero actualizado correctamente.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		response.put("message", "No se encontró el subgénero a editar.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@GetMapping("/subgenre/findId/{subgenre_id}")
	public ResponseEntity<?> findIdSubgenre( @PathVariable Integer subgenre_id)
	{
		Map<String, String> response = new HashMap<>();
		Subgenre subgenreDb=subgenreService.find_id(subgenre_id);
		
		if(subgenreDb!=null){		
			
			return new ResponseEntity<>(subgenreDb,HttpStatus.OK);
		}
		
		response.put("message", "Subgénero no encontrado.");
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@GetMapping("/subgenre/findSubgenre/{name}")
	public ResponseEntity<?> findSubgenre( @PathVariable String name)
	{
		Map<String, String> response = new HashMap<>();
		Collection<Subgenre> subgenreDb=subgenreService.findSubgenre(name);
		
		if(subgenreDb!=null){		
			
			return new ResponseEntity<>(subgenreDb,HttpStatus.OK);
		}
		
		response.put("message", "Subgénero no encontrado.");
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
