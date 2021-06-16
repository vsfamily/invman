package vsfam.ss.invMan.setup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.setup.domain.OrganizationUnitType;

public interface OrganizationUnitTypeRepo extends JpaRepository<OrganizationUnitType, Long> {

	OrganizationUnitType findByCode(String code);
	
	OrganizationUnitType findByDescription(String description);
}
