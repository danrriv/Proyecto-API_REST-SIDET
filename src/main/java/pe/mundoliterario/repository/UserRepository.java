package pe.mundoliterario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.mundoliterario.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM user where user_email=?1" ,nativeQuery = true)
	public abstract User find_email(String email);
	
	@Query(value = "SELECT * FROM user where user_token = :token" ,nativeQuery = true)
    User findByToken(String token);
	
	@Query(value = "SELECT * FROM user where user_surnames = :surnames" ,nativeQuery = true)
    User findBySurnames(String surnames);
}
