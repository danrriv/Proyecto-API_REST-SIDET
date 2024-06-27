package pe.mundoliterario.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.mundoliterario.service.BookService;
import pe.mundoliterario.util.MapperMundoLiterario;
import pe.mundoliterario.vo.BookStockDto;
import pe.mundoliterario.entity.Book;


@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired 
	BookService service;
	
	public BookController() {
		// TODO Auto-generated constructor stub
	}

	//Métodos CRUD
	@PostMapping("/save")
	public ResponseEntity<?> saveBook(@RequestBody Book book) {
		
		service.insert(book);

		Map<String, String> response = new HashMap<>();

		response.put("message", "Libro registrado correctamente.");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editBook(@RequestBody Book book, @PathVariable Integer id)

	{
		Book BookDb = service.findById(id);

		Map<String, String> response = new HashMap<>();

		if (BookDb != null) {

			BookDb.setBook_name(book.getBook_name());

			BookDb.setBook_weight(book.getBook_weight()); //peso

			BookDb.setBook_editorial(book.getBook_editorial());

			BookDb.setBook_width(book.getBook_width()); //alto
			
			BookDb.setBook_heigth(book.getBook_heigth()); //ancho
			
			BookDb.setBook_stock(book.getBook_stock());
			
			BookDb.setBook_price(book.getBook_price());

			BookDb.setBook_npages(book.getBook_npages());
			
			BookDb.setBook_year(book.getBook_year());
			
			BookDb.setBook_synopsis(book.getBook_synopsis());
			
			BookDb.setBook_status(book.getBook_status());
			
			BookDb.setBook_notification_status(book.getBook_notification_status());
			
			BookDb.setBook_img(book.getBook_img());

			BookDb.setAuthor(book.getAuthor());
		
			BookDb.setSubgenre(book.getSubgenre());

			service.update(BookDb);

			response.put("message", "Libro editado correctamente.");

			return ResponseEntity.status(HttpStatus.OK).body(response);

		}
		response.put("message", "404 E");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	//Actualizar stock
    @PutMapping("/updateStock/{book_id}")
    public ResponseEntity<?> update_stock(@PathVariable Integer book_id, @RequestBody BookStockDto dto) {
    	
    	Book book = service.findById(book_id);
    	
    	Map<String, String> response = new HashMap<>();
    	
    	if(book!=null) {
    		book.setBook_stock(book.getBook_stock() + dto.getStock());
    		service.update(book);
    		response.put("message", "Stock actulizado correctamente.");
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	}
    	response.put("message", "404 E");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); 	
    }
    
    @GetMapping("/findStock/{id}")
    public ResponseEntity<?> buscarStock(@PathVariable Integer id) {
        Book bookBd = service.findById(id);

        if (bookBd != null) {
            BookStockDto dto = new BookStockDto();
            dto.setStock(bookBd.getBook_stock());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>("¡Libro no existe!", HttpStatus.NOT_FOUND);
    }



	//Búsquedas
	@GetMapping("/findIdM/{id}") //Realizar búsqueda con el formato del Mapper
	public ResponseEntity<?> buscarLibroIdMapper(@PathVariable Integer id) {

		Book BookBd = service.findById(id);

		if (BookBd != null) {

			return new ResponseEntity<>(MapperMundoLiterario.toBooks(BookBd), HttpStatus.OK);

		}
		return new ResponseEntity<>("¡Libro no existe!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/findId/{id}") //Realizar búsqueda sin el formato del Mapper
	public ResponseEntity<?> buscarLibroId(@PathVariable Integer id) {

		Book BookBd = service.findById(id);

		if (BookBd != null) {

			return new ResponseEntity<>(BookBd, HttpStatus.OK);

		}
		return new ResponseEntity<>("¡Libro no existe!", HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/findName/{name}") //
	public ResponseEntity<?> findBookName(@PathVariable String name) {

		Book BookBd = service.findByName(name);

		if (BookBd != null) {

			return new ResponseEntity<>(BookBd, HttpStatus.OK);

		}
		return new ResponseEntity<>("¡Libro no existe!", HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/findSimilarName/{name}") //
	public ResponseEntity<?> findSimilarBookName(@PathVariable String name) {

		Collection<Book> books = service.findBySimilarName(name);

		if(!books.isEmpty()) {
			return new ResponseEntity<>(MapperMundoLiterario.toBook(books), HttpStatus.OK);
		}
		return new ResponseEntity<>("¡Libro no existe!", HttpStatus.NOT_FOUND);
	}
	
	
	//Listados
	@GetMapping("/random") //Realizar búsqueda al azar con el formato del Mapper
	public ResponseEntity<?> listRandom15() {

		Collection<Book> BookBd = service.listRandom9();

		if (BookBd != null) {

			return new ResponseEntity<>(MapperMundoLiterario.toBook(service.listRandom9()), HttpStatus.OK);

		}
		return new ResponseEntity<>(":(", HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<?> list_GET(){
		return new ResponseEntity<>(MapperMundoLiterario.toBook(service.findAll()), HttpStatus.OK);
	}
	
	
	@GetMapping("/findGenreId/{id}") 
	public ResponseEntity<?> findBookByGenre(@PathVariable Integer id) {

		Collection<Book> books  = service.listByGenre(id);

		if (!books.isEmpty()) {

			return new ResponseEntity<>(MapperMundoLiterario.toBook(books), HttpStatus.OK);

		}
		return new ResponseEntity<>("¡No hay libros registrados!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/findSubgenreId/{id}") 
	public ResponseEntity<?> findBookBySubgenre(@PathVariable Integer id) {

		Collection<Book> books  = service.listBySubgenre(id);

		if (!books.isEmpty()) {

			return new ResponseEntity<>(MapperMundoLiterario.toBook(books), HttpStatus.OK);

		}
		return new ResponseEntity<>("¡No hay libros registrados!", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/searchBySubgenre/{subgenre}")
	public ResponseEntity<?>searchBySubgenre(@PathVariable String subgenre) {
		Collection<Book> books = service.searchBySubgenre(subgenre);

		if (!books.isEmpty()) {
			return new ResponseEntity<>(books, HttpStatus.OK);
		}

		Map<String, String> response = new HashMap<>();
		response.put("message", "¡No hay libros registrados!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}

	
}