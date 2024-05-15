package pe.mundoliterario.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.mundoliterario.service.BookService;
import pe.mundoliterario.util.MapperMundoLiterario;
import pe.mundoliterario.entity.Book;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	@Autowired BookService service;
	
	public BookController() {
		// TODO Auto-generated constructor stub
	}
	/* 
	@GetMapping("/list")
	public ResponseEntity<?> list_GET(){
		return new ResponseEntity<>(MapperMundoLiterario.toBook(service.findAll()), HttpStatus.OK);
	}*/
	
	@PostMapping("/save")
	public ResponseEntity<?> save_POST(@RequestBody Book book) {

		service.insert(book);

		Map<String, String> response = new HashMap<>();

		response.put("message", "Libro registrado correctamente.");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/edit/{id_libro}")
	public ResponseEntity<?> editar_PUT(@RequestBody Book book, @PathVariable Integer id)

	{
		Book BookDb = service.findById(id);

		Map<String, String> response = new HashMap<>();

		if (BookDb != null) {

			BookDb.setName(book.getName());

			BookDb.setWeight(book.getWeight()); //peso

			BookDb.setEditorial(book.getEditorial());

			BookDb.setWidth(book.getWidth()); //alto 

			BookDb.setHeigth(book.getHeigth()); //ancho

			BookDb.setYear(book.getYear());

			BookDb.setNpages(book.getNpages());

			//BookDb.setNombre_autor(book.getNombre_autor());

			BookDb.setPrice(book.getPrice());

			BookDb.setImg(book.getImg());
			
			//LibroDb.setSubgenero(book.getSubgenero());

			service.update(BookDb);

			response.put("message", "Libro editado correctamente.");

			return ResponseEntity.status(HttpStatus.OK).body(response);

		}
		response.put("message", "404 E");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@DeleteMapping("/delete/{id_libro}")

	public ResponseEntity<?> borrar_DELETE(@PathVariable Integer id)

	{
		Book BookBd = service.findById(id);

		if (BookBd != null) {

			service.delete(id);

			return new ResponseEntity<>("¡Libro borrado!", HttpStatus.OK);

		}

		return new ResponseEntity<>("¡Libro no existe!", HttpStatus.NOT_FOUND);

	}

	@GetMapping("/search/{id_libro}")
	public ResponseEntity<?> buscarLibroId(@PathVariable Integer id) {

		Book BookBd = service.findById(id);

		/*if (BookBd != null) {

			return new ResponseEntity<>(MapperMundoLiterario.toBooks(BookBd), HttpStatus.OK);

		}*/
		return new ResponseEntity<>("¡Libro no existe!", HttpStatus.NOT_FOUND);
	}
	
}
