package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Subgenre;
import pe.mundoliterario.repository.SubgenreRepository;
import pe.mundoliterario.service.SubgenreService;

@Service
public class SubgenreServiceImpl implements SubgenreService{
	
	@Autowired
	SubgenreRepository subgenreRepository;

	@Override
	@Transactional
	public void create(Subgenre subgenre) {
		subgenreRepository.save(subgenre);
		
	}

	@Override
	@Transactional
	public void update(Subgenre subgenre) {
		subgenreRepository.save(subgenre);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Subgenre find_id(Integer subgenre_id) {
		return subgenreRepository.findById(subgenre_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Subgenre> list() {
		return subgenreRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Subgenre> findSubgenre(String name) {
		return subgenreRepository.findSugenre(name);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Subgenre> findUnassignedSubgenresByGenreId(Integer genre_id) {
		return subgenreRepository.findUnassignedSubgenresByGenreId(genre_id);
	}

}
