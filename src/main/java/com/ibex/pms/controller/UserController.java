package com.ibex.pms.controller;

import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.UserDto;
import com.ibex.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping()
    public List<UserDto> getAllUsers(){

       return  userService.getAllUsers();
    }
    @GetMapping("/customers")
    public List<UserDto> getAllCustomers(){
        return userService.getAllCustomers();
    }
    @GetMapping("/customers/{customerId}")
    public UserDto getCustomerById(@PathVariable long customerId){

        return userService.getCustomerById(customerId);

    }
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomerById(@PathVariable long customerId){

         userService.deleteCustomerById(customerId);

    }



    @GetMapping("/owners")
    public List<UserDto> getAllOwners(){
        return userService.getAllOwners();
    }
    @GetMapping("/owners/{ownerId}")
    public UserDto getOwnerById(@PathVariable long ownerId){

        return userService.getOwnerById(ownerId);

    }

    @DeleteMapping("/owners/{ownerId}")
    public void deleteOwnerById(@PathVariable long ownerId){

        userService.deleteOwnerById(ownerId);

    }


    @GetMapping("/admins")
    public List<UserDto> getAllAdmins(){

        return userService.getAllAdmin();
    }

    @GetMapping("/admins/{adminId}")
    public UserDto getAdminById(@PathVariable long adminId){
        return userService.getAdminById(adminId);
    }

    @DeleteMapping("/admins/{adminId}")
    public void deleteAdminById(@PathVariable long adminId){
        userService.deleteAdminById(adminId);

    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(long userId){

        userService.deleteUser(userId);

    }
    @PutMapping("/{id}")
    public  void updateUserById(@RequestBody User user, @PathVariable long id){

        userService.updateUserById(user, id);

    }


















}
