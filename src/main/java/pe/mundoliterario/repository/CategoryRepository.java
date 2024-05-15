package pe.mundoliterario.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.mundoliterario.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT * FROM category WHERE category_name = :name", nativeQuery = true)
    Collection<Category> findCategory(@Param("name") String name);

	 @Query(value = "SELECT c.category_id, c.category_name AS category_name,\r\n"
	 		+ "		 g.genre_id, g.genre_name AS genre_name,\r\n"
	 		+ "		 s.subgenre_id, s.subgenre_name AS subgenre_name\r\n"
	 		+ "		 FROM Category c\r\n"
	 		+ "		 LEFT JOIN Genre g ON c.category_id = g.category_id\r\n"
	 		+ "		 LEFT JOIN genre_subgenre gs ON g.genre_id = gs.genre_id\r\n"
	 		+ "		 LEFT JOIN Subgenre s ON gs.subgenre_id = s.subgenre_id"
	 		, nativeQuery = true)
		 List <Object[]> listCategoryGenreSubgenreMenu();	
}
