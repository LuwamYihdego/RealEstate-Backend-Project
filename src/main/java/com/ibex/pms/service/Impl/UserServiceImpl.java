package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;
import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.UserDto;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.repository.RoleRepo;
import com.ibex.pms.repository.UserRepo;
import com.ibex.pms.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
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

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(long id) {
        return userRepo.findById(id).get();
    }

    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    public void save(UserDto user) {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());

        if (user.getAddress() != null && user.getAddress().getId() == 0) {
            Address address = addressRepo.getAddressByStreetEqualsIgnoreCase(user.getAddress().getStreet());
            if (address != null)
                newUser.setAddress(address);
            else {
                addressRepo.save(user.getAddress());
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

    public void update(long id, UserDto user) {
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

        User user = userRepo.findAll()
                .stream()
                .filter(s -> s.getId() == id && s.getRole().equals("customer")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

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
        User user = userRepo.findAll().stream().filter(u -> u.getId() == id && u.getRole().equals("owner")).findAny().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    // Deactivate Owner

    @Override
    public void deleteOwnerById(long id) {

        User owner = userRepo.findAll()
                .stream()
                .filter(s -> s.getId() == id && s.getRole().equals("owner")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        owner.setActive(Boolean.FALSE);
    }

    @Override
    public List<UserDto> getAllAdmin() {
        List<User> admins = userRepo.findAll().stream().filter(c -> c.getRole().equals("admin")).collect(Collectors.toList());
        List<UserDto> usersDto = Arrays.asList(mapper.map(admins, UserDto[].class));

        return usersDto;
    }

    public UserDto getAdminById(long id) {
        User admin = userRepo.findAll().stream().filter(u -> u.getId() == id && u.getRole().equals("admin")).findAny().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        ;
        UserDto adminDto = mapper.map(admin, UserDto.class);
        return adminDto;
    }
// Deactivate admin

    public void deleteAdminById(long id) {
        User admin = userRepo.findAll()
                .stream()
                .filter(s -> s.getId() == id && s.getRole().equals("admin")).findFirst().orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));

        admin.setActive(Boolean.FALSE);
    }
//    @Override
//    public void updateUserById(User user, long id) {
//
//        User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
//        u.setFirstName(user.getFirstName());
//        u.setLastName(user.getLastName());
//        u.setPhoneNumber(user.getPhoneNumber());
//        u.setRole(user.getRole());
//        u.setEmail(user.getEmail());
//        u.setPassword(user.getPassword());
//        u.setAddress(user.getAddress());
//        u.setPropertyList(user.getPropertyList());
//    }
}
