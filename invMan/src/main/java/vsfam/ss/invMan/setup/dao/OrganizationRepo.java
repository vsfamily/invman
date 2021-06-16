package vsfam.ss.invMan.setup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.setup.domain.Organization;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {

	Organization findByShortName(String shortName);
	
	Organization findByName(String name);
}
