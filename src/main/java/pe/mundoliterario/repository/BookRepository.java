package pe.mundoliterario.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.mundoliterario.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	//Buscar con el nombre exacto
	@Query(value = "SELECT * FROM book WHERE book_name = :name", nativeQuery = true)
    Book findBookbyName(@Param("name") String name);

	//Listar con nombres similares
	@Query(value= "SELECT * FROM book b WHERE LOWER(b.book_name) LIKE CONCAT('%',LOWER(:name),'%')", nativeQuery = true)
	Collection<Book> findBySimilarName(String name);
	
	//Listar al azar
	@Query(value= "SELECT * FROM book ORDER BY RAND() LIMIT 9", nativeQuery = true)
	Collection<Book> listRandom9();
	
	//Listar por género
	@Query(value= "SELECT book.*\r\n"
			+ "FROM book\r\n"
			+ "INNER JOIN subgenre ON book.subgenre_id = subgenre.subgenre_id\r\n"
			+ "INNER JOIN genre_subgenre ON subgenre.subgenre_id = genre_subgenre.subgenre_id\r\n"
			+ "INNER JOIN genre ON genre_subgenre.genre_id = genre.genre_id\r\n"
			+ "WHERE genre.genre_id = :id ORDER BY RAND() LIMIT 9", nativeQuery = true)
	Collection<Book> listbyGenre(Integer id);
	
	//Listar por subgénero
	@Query(value= "SELECT * FROM book where subgenre_id = :id", nativeQuery = true)
	Collection<Book> listbySubGenre(Integer id);
}
