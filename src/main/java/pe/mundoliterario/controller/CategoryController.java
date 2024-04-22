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

import pe.mundoliterario.entity.Category;
import pe.mundoliterario.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/category/list")
	public ResponseEntity<?> category_list(){
		Collection<Category> list = categoryService.list();
		
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping("/category/create")
	public ResponseEntity<?> category_create(@RequestBody Category category){
		categoryService.create(category);
		Map<String, String> response = new HashMap<>();
		  response.put("message", "Categoría registrado correctamente.");
		  return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/category/update/{category_id}")
	public ResponseEntity<?> category_update(@RequestBody Category category, @PathVariable Integer category_id){
		
		Map<String, String> response = new HashMap<>();
		Category categoryDb=categoryService.find_id(category_id);
		
		if(categoryDb!=null){
			
			category.setCategory_id(category_id);
			categoryService.update(category);
			
			response.put("message", "Categoría actualizada correctamente.");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		response.put("message", "No se encontró la categoría a editar.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@GetMapping("/category/findId/{category_id}")
	public ResponseEntity<?> findIdCategory( @PathVariable Integer category_id)
	{
		Map<String, String> response = new HashMap<>();
		Category categoryDb=categoryService.find_id(category_id);
		
		if(categoryDb!=null){
			
			return new ResponseEntity<>(categoryDb,HttpStatus.OK);
		}
		
		response.put("message", "Categoría no encontrada.");
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@GetMapping("/category/findCategory/{name}")
	public ResponseEntity<?> findCategory( @PathVariable String name)
	{
		Map<String, String> response = new HashMap<>();
		Collection<Category> categoryDb=categoryService.findCategory(name);
		
		if(categoryDb!=null){
			
			return new ResponseEntity<>(categoryDb,HttpStatus.OK);
		}
		
		response.put("message", "Categoría no encontrada.");
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
