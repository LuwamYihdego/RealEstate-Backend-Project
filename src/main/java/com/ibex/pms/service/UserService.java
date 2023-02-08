package com.ibex.pms.service;

import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.security.auth.login.CredentialException;
import java.util.List;

public interface UserService {

    UserDto getCustomerById(long id);
    UserDto getOwnerById(long id);
    void update(User user, long id);
    List<UserDto>getAllUsers();
    List<UserDto>getAllCustomers();
    List<UserDto>getAllOwners();
    List<UserDto>getAllAdmin();
    boolean isEmailExist(String email);
    void deleteCustomerById(long id);
    void deleteOwnerById(long id);

    void createUser(User user);


    void deleteUser(long id);


    // security allowed properties











    void updateUserById(User user, long id);




}
