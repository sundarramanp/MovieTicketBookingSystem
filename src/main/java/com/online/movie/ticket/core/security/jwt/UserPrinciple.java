package com.online.movie.ticket.core.security.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.online.movie.ticket.enumeration.LoginUserType;
import com.online.movie.ticket.enumeration.UserStatus;
import com.online.movie.ticket.model.admin.UserMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Getter
	private String loginID;

	@Getter
    private String username;
    @Getter
    private String userType;

    @JsonIgnore
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;
    
    @Getter
    @Setter
    private String token;
    
    public UserPrinciple(String loginID,
    		String username, UserStatus status,  String password, LoginUserType userType,
    		Collection<? extends GrantedAuthority> authorities) {
			this.loginID = loginID;	
			this.username = loginID;
			this.userType = userType.toString();
			this.password = password;
			this.authorities = authorities;
			
}
    
    public static UserPrinciple build(UserMaster user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UserPrinciple(
                user.getUserID(),
                user.getUserName(),
                user.getStatus(),
                user.getPassword(),
                user.getUserType(),
                authorities
        );
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
