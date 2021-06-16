package vsfam.ss.invMan.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.manager.domain.Group;

public interface GroupRepo extends JpaRepository<Group, Long>{

	Group findByCode(String code);
	
	Group findByDescription(String description);
	
}
