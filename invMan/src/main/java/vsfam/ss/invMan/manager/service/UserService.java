package vsfam.ss.invMan.manager.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.dao.RoleRepo;
import vsfam.ss.invMan.manager.dao.UserRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.manager.domain.Role;
import vsfam.ss.invMan.manager.domain.User;

@Service
public class UserService {
	
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Transactional
	public void initializeDatabase() {
		
		Role role = new Role();
		role.setCode("ROLE_MANAGER");
		role.setDescription("Role Manager");
		role = roleRepo.save(role);
		
		User user = new User();
		user.setFullName("Sanjay Singh");
		user.setPassword((new BCryptPasswordEncoder()).encode("admin"));
		user.setUid("admin");
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user = userRepo.save(user);
		
		Set<User> users = new HashSet<User>();
		users.add(user);
		role.setUsers(users);
		role = roleRepo.save(role);
	}
	
	@Transactional
	public TransactionResult addUser(User user, String updateBy) {
		
		user = userRepo.save(user);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDUser", user.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(user, true);
	}

	@Transactional
	public TransactionResult deleteUser(Long id, String updateBy) {

		Optional<User> oe = this.userRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		User obj = oe.get();
		
		if (obj.getUid().equals(updateBy))
			return new TransactionResult(false, "Cannot delete self.");

		String auditString = obj.getAuditString();

		userRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELUser", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateUser(User user, String updateBy) {
		
		Optional<User> oe = this.userRepo.findById(user.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		User obj = oe.get();
		
		if (obj.getUid().equals(updateBy))
			return new TransactionResult(false, "Cannot update self.");
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setFullName(user.getFullName());
		obj.setAccountNonExpired(user.isAccountNonExpired());
		obj.setAccountNonLocked(user.isAccountNonLocked());
		obj.setCredentialsNonExpired(user.isCredentialsNonExpired());
		obj.setEnabled(user.isCredentialsNonExpired());
		
		if (user.getPassword().length()>0) {
			obj.setPassword((new BCryptPasswordEncoder()).encode(user.getPassword()));
		}
		
		obj = userRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDUser", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
