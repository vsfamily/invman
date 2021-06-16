package vsfam.ss.invMan.setup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.setup.domain.Country;

public interface CountryRepo extends JpaRepository<Country, Long>{

	Country findByCode(String code);
	
	Country findByName(String name);
}
