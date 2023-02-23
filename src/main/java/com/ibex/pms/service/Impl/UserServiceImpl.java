package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Offer;
import com.ibex.pms.domain.Role;
import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.request.UserRequestDto;
import com.ibex.pms.domain.dto.response.OfferResponseDto;
import com.ibex.pms.domain.dto.response.UserResponseDto;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.repository.RoleRepo;
import com.ibex.pms.repository.UserRepo;
import com.ibex.pms.service.UserService;
import com.ibex.pms.util.JwtUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;


    private UserRepo userRepo;
    private ModelMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;
    private RoleRepo roleRepo;
    private final AddressRepo addressRepo;


    public UserServiceImpl(UserRepo repo, ModelMapper mapper,
                           RoleRepo roleRepo,
                           AddressRepo addressRepo) {
        this.userRepo = repo;
        this.mapper = mapper;
        this.roleRepo = roleRepo;
        this.addressRepo = addressRepo;
    }

    public List<UserResponseDto> getAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list
                .stream()
                .map(p -> mapper.map(p, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto getById(long id) {
        User user = userRepo.findById(id).get();
        UserResponseDto dto = mapper.map(user, UserResponseDto.class);
        return dto;
    }

    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    public void save(UserRequestDto user) {
        try {
            User newUser = new User();

            newUser.setEmail(user.getEmail());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setPassword(user.getPassword());

            if (user.getAddress() != null && user.getAddress().getId() == 0) {
                Address address = addressRepo.getAddressByStreetEqualsIgnoreCase(user.getAddress().getStreet());
                if (address != null)
                    newUser.setAddress(address);
                else {
                    address = user.getAddress();
                    addressRepo.save(address);
                    newUser.setAddress(user.getAddress());
                }
            }

            if (user.getRole() != null && user.getRole().getId() == 0) {
                Role role = roleRepo.getByRoleEqualsIgnoreCase(user.getRole().getRole());
                if (role != null)
                    newUser.setRole(role);
            }

            userRepo.save(newUser);
        }
        catch(Exception ex){
        }
    }

    public void update(long id, UserRequestDto user) {
        User record = entityManager.find(User.class, id);
        if (record != null) {
            record.setFirstName(user.getFirstName());
            record.setLastName(user.getLastName());
            record.setEmail(user.getEmail());
            record.setPhoneNumber(user.getPhoneNumber());
            record.setPassword(user.getPassword());
            record.setAddress(user.getAddress());
            record.setRole(user.getRole());
            record.setActive(user.isActive());
            entityManager.persist(record);
        }
    }

//    @Override
//    public List<UserResponseDto> getAllUsers() {
//        List<User> users = userRepo.findAll();
//        List<UserRequestDto> usersDto = Arrays.asList(mapper.map(users, UserRequestDto[].class));
//        return usersDto;
//    }
//
//    @Override
//    public List<UserResponseDto> getAllCustomers() {
//        List<User> customers = userRepo.findAll().stream().filter(c -> c.getRole().equals("customer")).collect(Collectors.toList());
//        List<UserRequestDto> usersDto = Arrays.asList(mapper.map(customers, UserRequestDto[].class));
//        return usersDto;
//    }
//
//    @Override
//    public UserResponseDto getCustomerById(long id) {
//        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
//        UserRequestDto userDto = mapper.map(user, UserRequestDto.class);
//        return userDto;
//    }
//
//    // Deactivate Customer
//    @Override
//    public void deleteCustomerById(long id) {
//
//        User user = userRepo.findAll()
//                .stream()
//                .filter(s -> s.getId() == id && s.getRole().equals("customer")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
//
//        user.setActive(Boolean.FALSE);
//    }
//
//    @Override
//    public List<UserResponseDto> getAllOwners() {
//
//        List<User> owners = userRepo.findAll().stream().filter(c -> c.getRole().equals("owner")).collect(Collectors.toList());
//        List<UserRequestDto> usersDto = Arrays.asList(mapper.map(owners, UserRequestDto[].class));
//
//        return usersDto;
//    }
//
//    @Override
//    public UserResponseDto getOwnerById(long id) {
//        User user = userRepo.findAll().stream().filter(u -> u.getId() == id && u.getRole().equals("owner")).findAny().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
//
//        UserRequestDto userDto = mapper.map(user, UserRequestDto.class);
//        return userDto;
//    }
//
//    // Deactivate Owner
//
//    @Override
//    public void deleteOwnerById(long id) {
//
//        User owner = userRepo.findAll()
//                .stream()
//                .filter(s -> s.getId() == id && s.getRole().equals("owner")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
//
//        owner.setActive(Boolean.FALSE);
//    }
//
//    @Override
//    public List<UserResponseDto> getAllAdmin() {
//        List<User> admins = userRepo.findAll().stream().filter(c -> c.getRole().equals("admin")).collect(Collectors.toList());
//        List<UserRequestDto> usersDto = Arrays.asList(mapper.map(admins, UserRequestDto[].class));
//
//        return usersDto;
//    }
//
//    public UserResponseDto getAdminById(long id) {
//        User admin = userRepo.findAll().stream().filter(u -> u.getId() == id && u.getRole().equals("admin")).findAny().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
//        ;
//        UserRequestDto adminDto = mapper.map(admin, UserRequestDto.class);
//        return adminDto;
//    }
//
//    public void deleteAdminById(long id) {
//        User admin = userRepo.findAll()
//                .stream()
//                .filter(s -> s.getId() == id && s.getRole().equals("admin")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
//
//        admin.setActive(Boolean.FALSE);
//    }
//
//    void test() {
//        System.out.println("test");
//    }
}
