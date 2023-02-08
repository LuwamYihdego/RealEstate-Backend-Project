package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.UserDto;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.UserRepo;
import com.ibex.pms.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper mapper;

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepo.findAll();

        List<UserDto> usersDto = Arrays.asList(mapper.map(users, UserDto[].class));

        return usersDto;
    }

    @Override
    public List<UserDto> getAllCustomers() {

        List<User> customers = userRepo.findAll().stream().filter(c -> c.getRole().equals("customer")).collect(Collectors.toList());
        List<UserDto> usersDto = Arrays.asList(mapper.map(customers, UserDto[].class));

        return usersDto;
    }

    @Override
    public UserDto getCustomerById(long id) {

        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }
    // Deactivate Customer
    @Override
    public void deleteCustomerById(long id) {

        User user =  userRepo.findAll()
                .stream()
                .filter(s->s.getId()==id && s.getRole().equals("customer")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));


        user.setDeleted(Boolean.TRUE);
        user.setActive(Boolean.FALSE);


    }


    @Override
    public List<UserDto> getAllOwners() {

        List<User> owners = userRepo.findAll().stream().filter(c -> c.getRole().equals("owner")).collect(Collectors.toList());
        List<UserDto> usersDto = Arrays.asList(mapper.map(owners, UserDto[].class));

        return usersDto;
    }

    @Override
    public UserDto getOwnerById(long id) {
        User user = userRepo.findAll().stream().filter(u -> u.getId()==id && u.getRole().equals("owner")).findAny().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    // Deactivate Owner

    @Override
    public void deleteOwnerById(long id) {

        User owner =  userRepo.findAll()
                .stream()
                .filter(s->s.getId()==id && s.getRole().equals("owner")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));


        owner.setDeleted(Boolean.TRUE);
        owner.setActive(Boolean.FALSE);



    }

    @Override
    public List<UserDto> getAllAdmin() {
        List<User> admins = userRepo.findAll().stream().filter(c -> c.getRole().equals("admin")).collect(Collectors.toList());
        List<UserDto> usersDto = Arrays.asList(mapper.map(admins, UserDto[].class));

        return usersDto;
    }

    public  UserDto getAdminById(long id){
        User admin = userRepo.findAll().stream().filter(u -> u.getId()==id && u.getRole().equals("admin")).findAny().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        ;
        UserDto adminDto = mapper.map(admin, UserDto.class);
        return adminDto;
    }
// Deactivate admin

    public void deleteAdminById(long id){
        User admin =  userRepo.findAll()
                .stream()
                .filter(s->s.getId()==id && s.getRole().equals("admin")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));


        admin.setDeleted(Boolean.TRUE);
        admin.setActive(Boolean.FALSE);

    }



    @Override
    public void createUser(User user) {
        userRepo.save(user);

    }

    // Delete user from DB

    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);

    }



    @Override
    public void updateUserById(User user, long id) {

        User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        u.setRole(user.getRole());
        u.setUserDetails(user.getUserDetails());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());



    }

















}
