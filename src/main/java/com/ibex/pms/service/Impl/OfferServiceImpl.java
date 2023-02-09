package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Offer;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.repository.OfferRepo;
import com.ibex.pms.service.AddressService;
import com.ibex.pms.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    OfferRepo repo;
    public OfferServiceImpl(OfferRepo repo){
        this.repo = repo;
    }
    public List<Offer> getAll() {
        return repo.findAll();
    }
    public Offer getById(long id){
        return repo.findById(id).get();
    }
    public void deleteById(long id){
        repo.deleteById(id);
    }
    public void  save(Offer offer){
        repo.save(offer);
    }
    public void  update(long id, Offer offer){
        //TODO: Implement
    }
}
