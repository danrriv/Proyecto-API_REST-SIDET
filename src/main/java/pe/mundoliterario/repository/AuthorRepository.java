package pe.mundoliterario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.mundoliterario.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
