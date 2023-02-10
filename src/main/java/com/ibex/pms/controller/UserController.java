package com.ibex.pms.controller;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;
import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.UserDto;
import com.ibex.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable(name="id") Long id) {
        User user = userService.getById(id);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name="id")Long id) {
        userService.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody User user) {
//        Address address = user.getAddress();
//
//
//        Role role = user.getRole();
//        if(role != null){
//
//        }

        userService.save(user);
    }

    @PutMapping("/{id}")
    public void updateById(@RequestBody User user,
                           @PathVariable Long id) {
        userService.update(id, user);
    }

    @GetMapping("/customers")
    public List<UserDto> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public UserDto getCustomerById(@PathVariable long customerId) {
        return userService.getCustomerById(customerId);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomerById(@PathVariable long customerId) {
        userService.deleteCustomerById(customerId);
    }

    @GetMapping("/owners")
    public List<UserDto> getAllOwners() {
        return userService.getAllOwners();
    }

    @GetMapping("/owners/{ownerId}")
    public UserDto getOwnerById(@PathVariable long ownerId) {
        return userService.getOwnerById(ownerId);
    }

    @DeleteMapping("/owners/{ownerId}")
    public void deleteOwnerById(@PathVariable long ownerId) {
        userService.deleteOwnerById(ownerId);
    }


    @GetMapping("/admins")
    public List<UserDto> getAllAdmins() {
        return userService.getAllAdmin();
    }

    @GetMapping("/admins/{adminId}")
    public UserDto getAdminById(@PathVariable long adminId) {
        return userService.getAdminById(adminId);
    }

    @DeleteMapping("/admins/{adminId}")
    public void deleteAdminById(@PathVariable long adminId) {
        userService.deleteAdminById(adminId);
    }
}