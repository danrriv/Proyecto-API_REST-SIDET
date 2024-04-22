package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Subgenre;

public interface SubgenreService {

	public abstract void create(Subgenre subgenre);
	public abstract void update(Subgenre subgenre);
	public abstract Subgenre find_id(Integer subgenre_id);
	public abstract Collection<Subgenre> list();
	public abstract Collection<Subgenre> findSubgenre(String name);
	public abstract Collection<Subgenre> findUnassignedSubgenresByGenreId(Integer genre_id);
}
