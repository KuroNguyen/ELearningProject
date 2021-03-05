package com.myclass.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.myclass.filter.AuthFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	public AdminSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	// Bean for login verification
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**", "/api/admin/auth/login");
	}


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();

		http.csrf().disable() // turn off fraudulent prevention

			.antMatcher("/api/admin/**").authorizeRequests() // only apply access control for url start with /api/admin
			.antMatchers("/api/admin/auth/login").permitAll() 
			.antMatchers("/api/auth/login").permitAll()
			.antMatchers("/api/admin/role**").hasAnyRole("ADMIN")
			.antMatchers("/api/admin/category**").hasAnyRole("ADMIN", "TEACHER")
			.antMatchers("/api/admin/human**").hasAnyRole("ADMIN", "TEACHER")
			.antMatchers("/api/admin/user**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
			.anyRequest().authenticated();
		
		
		//
//.authorizeRequests()
//		
//		.antMatchers("/api/admin/auth/login","/api/admin/user").permitAll()
//		.and()
//		
//		.authorizeRequests()
//		.antMatchers("/api/admin/human**").hasAnyRole("ADMIN")
//		.and()
//	
//		.authorizeRequests()
//		.antMatchers("/api/auth/login").hasAnyRole("USER")
//		.and()
//		
//		.authorizeRequests()
//		.antMatchers("/api/admin/role**").hasAnyRole("ADMIN")
//		.and()
//		
//		.authorizeRequests()
//		.antMatchers("/api/admin/user**").hasAnyRole("ADMIN","TEACHER")
//		.and()
//		
//		.authorizeRequests()
//		.antMatchers("/api/admin/category**").hasAnyRole("ADMIN","TEACHER")
//		.and()
//		.authorizeRequests()
//		.antMatchers("/api/admin/**")
//		.authenticated();

		http.addFilter(new AuthFilter(authenticationManager(), userDetailsService));
		// Not use session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
