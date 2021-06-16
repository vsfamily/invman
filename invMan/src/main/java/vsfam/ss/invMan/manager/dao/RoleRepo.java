package vsfam.ss.invMan.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.manager.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

	Role findByCode(String code);
	
	Role findByDescription(String description);
}
