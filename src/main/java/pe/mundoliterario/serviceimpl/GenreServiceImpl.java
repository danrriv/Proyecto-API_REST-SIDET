package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Genre;
import pe.mundoliterario.repository.GenreRepository;
import pe.mundoliterario.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	GenreRepository genreRepository;
	
	@Override
	@Transactional
	public void create(Genre genre) {
		genreRepository.save(genre);
		
	}

	@Override
	@Transactional
	public void update(Genre genre) {
		genreRepository.save(genre);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Genre find_id(Integer genus_id) {
		return genreRepository.findById(genus_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Genre> list() {
		return genreRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Genre> findGenre(String name) {
		return genreRepository.findGenre(name);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Object[]> listGenreSubId() {
		return genreRepository.listGenreSubId();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Object[]> listGenreSub() {
		return genreRepository.listGenreSub();
	}

}
