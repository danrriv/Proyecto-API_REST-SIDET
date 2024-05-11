package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.User;
import pe.mundoliterario.repository.UserRepository;
import pe.mundoliterario.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public void create(User user) {
		userRepository.save(user);
		
	}

	@Override
	@Transactional
	public void update(User user) {
		userRepository.save(user);
		
	}

	@Override
	@Transactional(readOnly=true)
	public User find_id(Integer user_id) {
		return userRepository.findById(user_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<User> list() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public User find_email(String email) {
		return userRepository.find_email(email);
	}

	@Override
	public User findByToken(String token) {
		return userRepository.findByToken(token);
	}

	@Override
	@Transactional(readOnly=true)
	public User findBySurnames(String surnames) {
		return userRepository.findBySurnames(surnames);
	}

}
