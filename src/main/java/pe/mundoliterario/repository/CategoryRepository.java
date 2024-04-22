package pe.mundoliterario.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.mundoliterario.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT * FROM category WHERE category_name = :name", nativeQuery = true)
    Collection<Category> findCategory(@Param("name") String name);

}
