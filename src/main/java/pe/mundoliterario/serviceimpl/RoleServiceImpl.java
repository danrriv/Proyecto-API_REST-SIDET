package pe.mundoliterario.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.mundoliterario.entity.Role;
import pe.mundoliterario.repository.RoleRepository;
import pe.mundoliterario.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional(readOnly=true)
	public Role find_idRole(Integer role_id) {
		return roleRepository.findById(role_id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Role> listRoles() {
		return roleRepository.findAll();
	}

}
