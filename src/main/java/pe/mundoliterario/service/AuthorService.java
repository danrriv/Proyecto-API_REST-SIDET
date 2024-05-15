package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Author;

public interface AuthorService {

    public abstract void insert(Author author);

    public abstract void update(Author author);

    public abstract void delete(Integer author_id);

    public abstract Author findById(Integer author_id);
    
    public abstract Collection<Author> findAll();
}
