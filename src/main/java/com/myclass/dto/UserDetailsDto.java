package com.myclass.dto;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsDto extends User {
	
	private String fullname;
	private String avatar;

	public UserDetailsDto(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public UserDetailsDto(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String fullname, String avatar) {
		super(username, password, authorities);
		this.fullname = fullname;
		this.avatar = avatar;
	}

}
