package com.ibex.pms.controller;

import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.request.UserRequestDto;
import com.ibex.pms.domain.dto.response.UserResponseDto;
import com.ibex.pms.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public void getById(@PathVariable(name = "id") Long id) {
        userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        userService.deleteById(id);
    }

    @PostMapping
    public void save(@RequestBody UserRequestDto user) {
        userService.save(user);
    }

    @PutMapping("/{id}")
    public void updateById(@RequestBody UserRequestDto user,
                           @PathVariable Long id) {
        userService.update(id, user);
    }

//    @GetMapping("/customers")
//    public List<UserRequestDto> getAllCustomers() {
//        return userService.getAllCustomers();
//    }
//
//    @GetMapping("/customers/{customerId}")
//    public UserRequestDto getCustomerById(@PathVariable long customerId) {
//        return userService.getCustomerById(customerId);
//    }
//
//    @DeleteMapping("/customers/{customerId}")
//    public void deleteCustomerById(@PathVariable long customerId) {
//        userService.deleteCustomerById(customerId);
//    }
//
//    @GetMapping("/owners")
//    public List<UserRequestDto> getAllOwners() {
//        return userService.getAllOwners();
//    }
//
//    @GetMapping("/owners/{ownerId}")
//    public UserRequestDto getOwnerById(@PathVariable long ownerId) {
//        return userService.getOwnerById(ownerId);
//    }
//
//    @DeleteMapping("/owners/{ownerId}")
//    public void deleteOwnerById(@PathVariable long ownerId) {
//        userService.deleteOwnerById(ownerId);
//    }
//
//
//    @GetMapping("/admins")
//    public List<UserRequestDto> getAllAdmins() {
//        return userService.getAllAdmin();
//    }
//
//    @GetMapping("/admins/{adminId}")
//    public UserRequestDto getAdminById(@PathVariable long adminId) {
//        return userService.getAdminById(adminId);
//    }
//
//    @DeleteMapping("/admins/{adminId}")
//    public void deleteAdminById(@PathVariable long adminId) {
//        userService.deleteAdminById(adminId);
//    }
}