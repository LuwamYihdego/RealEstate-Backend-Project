package com.ibex.pms.service;

import com.ibex.pms.domain.Role;
import com.ibex.pms.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {
    private String email;
    private String password;
    private SimpleGrantedAuthority authorities;

    public MyUserDetails(User user){

        this.email = user.getEmail();
        this.password = user.getPassword();

        Role role = user.getRole();
        //this.authorities = (user.getRole()).stream().map(role -> role.getRole()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.authorities = new SimpleGrantedAuthority(role.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
