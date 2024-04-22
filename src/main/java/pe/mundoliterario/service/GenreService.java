package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Genre;

public interface GenreService {
	
	public abstract void create(Genre genre);
	public abstract void update(Genre genre);
	public abstract Genre find_id(Integer genre_id);
	public abstract Collection<Genre> list();
	public abstract Collection<Genre> findGenre(String name);
	
	public abstract Collection<Object[]> listGenreSubId();
	public abstract Collection<Object[]> listGenreSub();

}
