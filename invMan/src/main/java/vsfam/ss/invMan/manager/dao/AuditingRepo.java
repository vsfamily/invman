package vsfam.ss.invMan.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import vsfam.ss.invMan.manager.domain.Auditing;

public interface AuditingRepo extends JpaRepository<Auditing, Long>{

}
