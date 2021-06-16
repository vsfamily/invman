package vsfam.ss.invMan.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.manager.dao.UserRepo;
import vsfam.ss.invMan.manager.domain.Group;
import vsfam.ss.invMan.manager.domain.Role;
import vsfam.ss.invMan.manager.domain.User;
import vsfam.ss.invMan.manager.service.UserService;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (username == null) {
			throw new UsernameNotFoundException("Username was empty");
		}
		
		if (userRepo.count()==0) {
			userService.initializeDatabase();
		}
		
		//System.out.println("User: Searching for user... " + username);
		
		User user = userRepo.findByUid(username);
		
		if (user==null) {
			//System.out.println("User: User not found.");
			throw new UsernameNotFoundException("Username was not found");
		}
		
		//System.out.println("User: " + user.getFullName());
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for(Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
			System.out.println("Role: " + role.getCode());
		}
		
		for(Group group : user.getGroups()) {
			for(Role role : group.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
				System.out.println("Role: " + role.getCode());
			}
		}
		
		return new SecurityUserDetails(user, grantedAuthorities);
	}

}
