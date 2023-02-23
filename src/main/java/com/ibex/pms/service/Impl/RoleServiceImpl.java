package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.repository.RoleRepo;
import com.ibex.pms.service.AddressService;
import com.ibex.pms.service.RoleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    RoleRepo roleRepo;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public RoleServiceImpl(RoleRepo repo) {
        this.roleRepo = repo;
    }

    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    public Role getById(long id) {
        return roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id:" + id));
    }

    public void deleteById(long id) {
        roleRepo.deleteById(id);
    }

    public void save(Role role) {
        if (roleRepo.getByRoleEqualsIgnoreCase(role.getRole()) == null)
            roleRepo.save(role);
        else
            throw new ResourceNotFoundException("Duplicate role");
    }

    public void update(long id, Role role) {
        Role r = roleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id:" + id));
        r.setRole(role.getRole());
        em.persist(r);
    }
}
