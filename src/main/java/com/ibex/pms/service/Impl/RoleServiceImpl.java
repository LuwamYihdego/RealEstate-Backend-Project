package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.repository.RoleRepo;
import com.ibex.pms.service.AddressService;
import com.ibex.pms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepo repo;
    public RoleServiceImpl(RoleRepo repo){
        this.repo = repo;
    }
    public List<Role> getAll() {
        return repo.findAll();
    }
    public Role getById(long id){
        return repo.findById(id).get();
    }
    public void deleteById(long id){
        repo.deleteById(id);
    }
    public void  save(Role role){
        repo.save(role);
    }
    public void  update(long id, Role role){
        //TODO: Implement
    }
}
