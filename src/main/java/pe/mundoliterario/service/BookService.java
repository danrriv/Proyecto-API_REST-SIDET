package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Book;

public interface BookService {

    public abstract void insert(Book book);

    public abstract void update(Book book);

    public abstract void delete(Integer book_id);

    public abstract Book findById(Integer book_id);
    
    public abstract Collection<Book> findAll();
}
