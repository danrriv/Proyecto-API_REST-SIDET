package pe.mundoliterario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.mundoliterario.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
