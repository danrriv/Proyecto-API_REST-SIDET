package pe.mundoliterario.service;

import java.util.Collection;

import pe.mundoliterario.entity.Role;

public interface RoleService {
	
	public abstract Role find_idRole(Integer role_id);
	public abstract Collection<Role> listRoles();

}
