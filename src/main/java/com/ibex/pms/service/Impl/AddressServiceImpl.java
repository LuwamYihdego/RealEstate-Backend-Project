package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    AddressRepo repo;
    public AddressServiceImpl(AddressRepo repo){
        this.repo = repo;
    }
    public List<Address> getAll() {
        return repo.findAll();
    }
    public Address getById(long id){
        return repo.findById(id).get();
    }
    public void deleteById(long id){
        repo.deleteById(id);
    }
    public void  save(Address address){
        repo.save(address);
    }
    public void  update(long id, Address address){
        //TODO: Implement
    }
}
