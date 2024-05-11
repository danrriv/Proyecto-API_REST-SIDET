package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.User;

public interface UserService {
	
	public abstract void create(User user);
	public abstract void update(User user);
	public abstract User find_id(Integer user_id);
	public abstract Collection<User> list();
	public abstract User find_email(String email);
	public abstract User findByToken(String token);
	public abstract User findBySurnames(String surnames);

}
