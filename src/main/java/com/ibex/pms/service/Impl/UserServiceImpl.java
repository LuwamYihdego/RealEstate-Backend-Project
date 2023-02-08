package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.UserDto;
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
    public UserDto getCustomerById(long id) {

        User user = userRepo.findById(id).orElse(null);
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getOwnerById(long id) {
        User user = userRepo.findAll().stream().filter(u -> u.getId()==id && u.getRole().equals("owner")).findAny().get();
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public void update(User user, long id) {

    }
    // List<PostDTO> postDtoList = Arrays.asList(modelMapper.map(postEntityList, PostDTO[].class));
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
    public List<UserDto> getAllOwners() {

        List<User> owners = userRepo.findAll().stream().filter(c -> c.getRole().equals("owner")).collect(Collectors.toList());
        List<UserDto> usersDto = Arrays.asList(mapper.map(owners, UserDto[].class));

        return usersDto;
    }

    @Override
    public List<UserDto> getAllAdmin() {
        List<User> admins = userRepo.findAll().stream().filter(c -> c.getRole().equals("admin")).collect(Collectors.toList());
        List<UserDto> usersDto = Arrays.asList(mapper.map(admins, UserDto[].class));

        return usersDto;
    }

    @Override
    public boolean isEmailExist(String email) {
        return false;
    }

    @Override
    public void deleteCustomerById(long id) {

       User user =  userRepo.findAll()
                .stream()
                .filter(s->s.getId()==id && s.getRole().equals("customer")).findFirst().orElse(null);

       user.setDeleted(Boolean.TRUE);
       user.setActive(Boolean.FALSE);


    }
    @Override
    public void deleteOwnerById(long id) {

        User owner =  userRepo.findAll()
                .stream()
                .filter(s->s.getId()==id && s.getRole().equals("owner")).findFirst().orElse(null);

        owner.setDeleted(Boolean.TRUE);
        owner.setActive(Boolean.FALSE);



    }

    @Override
    public void createUser(User user) {
        userRepo.save(user);

    }

    @Override
    public void deleteUser(long id) {
        userRepo.deleteById(id);

    }



    @Override
    public void updateUserById(User user, long id) {

        User u = userRepo.findById(id).orElse(null);

        u.setRole(user.getRole());
        u.setUserDetails(user.getUserDetails());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());



    }
}
