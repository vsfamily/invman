package vsfam.ss.invMan.setup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.setup.domain.State;

public interface StateRepo extends JpaRepository<State, Long>{

	State findByCode(String code);
	
	State findByName(String name);
}
