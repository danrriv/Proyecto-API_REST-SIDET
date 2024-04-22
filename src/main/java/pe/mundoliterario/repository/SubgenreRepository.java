package pe.mundoliterario.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.mundoliterario.entity.Subgenre;

public interface SubgenreRepository extends JpaRepository<Subgenre, Integer>{

	@Query(value = "SELECT * FROM subgenre WHERE subgenre_name = :name", nativeQuery = true)
    Collection<Subgenre> findSugenre(@Param("name") String name);
	
	@Query(value = "SELECT * FROM subgenre s WHERE subgenre_id NOT IN (SELECT DISTINCT gs.subgenre_id FROM genre_subgenre gs WHERE gs.genre_id = :genre_id)", nativeQuery = true)
	Collection<Subgenre> findUnassignedSubgenresByGenreId(@Param("genre_id") Integer genre_id);
	
}
