package com.ibex.pms.service;

import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.request.UserRequestDto;
import com.ibex.pms.domain.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    /*
     *
     * for eager load
     * */

    List<UserResponseDto> getAll();
    UserResponseDto getById(long id);
    void deleteById(long id);
    void save(UserRequestDto user);
    void update(long id, UserRequestDto user);

    /*
     * End for eager load
     * */

//    UserResponseDto getCustomerById(long id);
//    UserResponseDto getOwnerById(long id);
//    UserResponseDto getAdminById(long id);
//    List<UserResponseDto> getAllUsers();
//    List<UserResponseDto>getAllCustomers();
//    List<UserResponseDto>getAllOwners();
//    List<UserResponseDto>getAllAdmin();
//    void deleteCustomerById(long id);
//    void deleteOwnerById(long id);
//    void deleteAdminById(long id);
    //void updateUserById(User user, long id);
}