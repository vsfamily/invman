package vsfam.ss.invMan.setup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.setup.domain.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Long>{

	Designation findByCode(String code);
	
	Designation findByDescription(String description);
}
