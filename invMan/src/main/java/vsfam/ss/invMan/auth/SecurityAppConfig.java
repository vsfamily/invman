package vsfam.ss.invMan.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityAppConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.anonymous()
			.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/contacts").permitAll()
				.antMatchers("/helpDocuments").permitAll()
				.antMatchers("/images").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/home").hasAnyRole("MANAGER", "SETUP", "USER")
				.antMatchers("/home/**").hasAnyRole("MANAGER", "SETUP", "USER")
				.antMatchers("/manager*").hasAnyRole("MANAGER")
				.antMatchers("/manager/**").hasAnyRole("MANAGER")
				.antMatchers("/report").hasAnyRole("MANAGER", "REPORT")
				.antMatchers("/report/**").hasAnyRole("MANAGER", "REPORT")
				.antMatchers("/setup*").hasAnyRole("MANAGER", "SETUP")
				.antMatchers("/setup/**").hasAnyRole("MANAGER", "SETUP")
				.antMatchers("/templates*").permitAll()
				.antMatchers("/templates/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
			.and()
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/home",true)
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success").permitAll();
	}
}
