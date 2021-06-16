package vsfam.ss.invMan.setup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.setup.domain.OrganizationUnit;

public interface OrganizationUnitRepo extends JpaRepository<OrganizationUnit, Long> {

	OrganizationUnit findByShortName(String shortName);
	
	OrganizationUnit findByName(String name);
}
