package vsfam.ss.invMan.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.manager.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUid(String uid);
}
