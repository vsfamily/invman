package vsfam.ss.invMan.setup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.setup.domain.City;

public interface CityRepo extends JpaRepository<City, Long> {

	City findByCode(String code);
	
	City findByName(String name);
}
