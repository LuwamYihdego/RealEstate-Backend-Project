package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Role;
import com.ibex.pms.domain.User;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isActive;

    @JsonIgnore
    private String password;
    private Role role;
    //private SimpleGrantedAuthority authorities;

    public UserDetails(User user) {

        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.isActive = user.isActive();

        //Role role = user.getRole();
        //this.authorities = (user.getRole()).stream().map(role -> role.getRole()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        //this.authorities = new SimpleGrantedAuthority(role.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        //return Collections.singleton(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
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
        return isActive;
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
