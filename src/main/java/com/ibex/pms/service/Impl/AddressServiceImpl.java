package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.service.AddressService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    AddressRepo addressRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public AddressServiceImpl(AddressRepo repo){
        this.addressRepo= repo;
    }
    public List<Address> getAll() {
        return addressRepo.findAll();
    }
    public Address getById(long id){
        return addressRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Addres not found with id:" + id));
    }
    public void deleteById(long id){
        addressRepo.deleteById(id);
    }
    public void  save(Address address){
        addressRepo.save(address);
    }
    public void  update(long id, Address address){
       Address ad = addressRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offer not found with id:" + id));
       ad.setCity(address.getCity());
       ad.setState(address.getState());
       ad.setStreet(address.getStreet());
       ad.setZipCode(address.getZipCode());

       entityManager.persist(ad);
    }
}
