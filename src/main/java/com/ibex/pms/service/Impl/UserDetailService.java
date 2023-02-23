package com.ibex.pms.service.Impl;

import com.ibex.pms.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    public UserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmailEqualsIgnoreCase(username);
        var userDetails = new UserDetails(user);
        return userDetails;
    }
}
