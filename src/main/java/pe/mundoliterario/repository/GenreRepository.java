package pe.mundoliterario.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.mundoliterario.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

	@Query(value = "SELECT * FROM genre WHERE genre_name = :name", nativeQuery = true)
    Collection<Genre> findGenre(@Param("name") String name);
	
	@Query(value = "select * from genre_subgenre", nativeQuery=true)
	public abstract Collection<Object[]> listGenreSubId();
	
	@Query(value = "select gen.genre_name as 'genre',\r\n"
			+ " subg.subgenre_name as 'subgenre'\r\n"
			+ "from genre_subgenre gs\r\n"
			+ "inner join genre gen on gs.genre_id = gen.genre_id\r\n"
			+ "inner join subgenre subg on gs.subgenre_id=subg.subgenre_id\r\n"
			+ "order by gen.genre_id, subg.subgenre_id;", nativeQuery=true)
	public abstract Collection<Object[]> listGenreSub();
	
}
