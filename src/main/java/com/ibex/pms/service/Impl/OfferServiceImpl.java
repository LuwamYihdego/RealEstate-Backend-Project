package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Offer;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.AddressRepo;
import com.ibex.pms.repository.OfferRepo;
import com.ibex.pms.service.AddressService;
import com.ibex.pms.service.OfferService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Service
public class OfferServiceImpl implements OfferService {
    OfferRepo offerRepo;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public OfferServiceImpl(OfferRepo repo){
        this.offerRepo = repo;
    }
    public List<Offer> getAll() {
        return offerRepo.findAll();
    }
    public Offer getById(long id){
        return offerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offer not found with id:" + id));
    }
    public void deleteById(long id){
        offerRepo.deleteById(id);
    }
    public void  save(Offer offer){
        offerRepo.save(offer);
    }
    public void  update(long id, Offer offer){

        Offer existingOffer =  offerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Offer not found with id:" + id));
        existingOffer.setAcceptance(offer.getAcceptance());
        existingOffer.setBuyer(offer.getBuyer());
        existingOffer.setBuyerProposedPrice(offer.getBuyerProposedPrice());
        existingOffer.setProperty(offer.getProperty());
        em.persist(existingOffer);

    }
}
