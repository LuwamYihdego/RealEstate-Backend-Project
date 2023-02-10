package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.BuyerDetails;
import com.ibex.pms.domain.Offer;
import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.OfferRequestDto;
import com.ibex.pms.domain.dto.OfferResponseDto;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.BuyerDetailsRepo;
import com.ibex.pms.repository.OfferRepo;
import com.ibex.pms.repository.PropertyRepo;
import com.ibex.pms.repository.UserRepo;
import com.ibex.pms.service.OfferService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {
    OfferRepo offerRepo;
    @PersistenceContext
    EntityManager em;
    private final PropertyRepo propertyRepo;
    private final UserRepo userRepo;
    private final BuyerDetailsRepo buyerDetailsRepo;
    private final ModelMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepo repo,
                            PropertyRepo propertyRepo,
                            BuyerDetailsRepo buyerDetailsRepo,
                            UserRepo userRepo,
                            ModelMapper mapper) {
        this.offerRepo = repo;
        this.propertyRepo = propertyRepo;
        this.userRepo = userRepo;
        this.buyerDetailsRepo = buyerDetailsRepo;
        this.mapper = mapper;
    }

    public List<OfferResponseDto> getAll() {
        List<Offer> list = new ArrayList<>();
        offerRepo.findAll().forEach(list::add);
        return list
                .stream()
                .map(p -> mapper.map(p, OfferResponseDto.class))
                .collect(Collectors.toList());
    }

    public OfferResponseDto getById(long id) {
        Offer offer = offerRepo.findById(id).get();
        OfferResponseDto dto = mapper.map(offer, OfferResponseDto.class);
        return dto;
    }

    public void deleteById(long id) {
        offerRepo.deleteById(id);
    }

    public void save(OfferRequestDto offerDto) {
        Offer offer = new Offer();
        offer.setProperty(propertyRepo.findById(offerDto.getPropertyId()).get());

        User user = userRepo.findById(offerDto.getBuyerId()).get();
        BuyerDetails buyer = new BuyerDetails();
        buyer.setFirstName(user.getFirstName());
        buyer.setLastName(user.getLastName());
        buyer.setEmail(user.getEmail());
        buyer.setPhoneNumber(user.getPhoneNumber());
        buyer.setAddress(user.getAddress());
        buyerDetailsRepo.save(buyer);

        offer.setBuyer(buyer);
        offer.setAcceptance(offerDto.getAcceptance());
        offer.setBuyerProposedPrice(offerDto.getBuyerProposedPrice());
        offerRepo.save(offer);
    }

    public void update(long id, OfferRequestDto offerDto) {
        Offer offer = offerRepo.findById(id).get();
        if (offer != null) {
            offer.setAcceptance(offerDto.getAcceptance());
            offer.setBuyerProposedPrice(offerDto.getBuyerProposedPrice());
            em.persist(offer);
        }
    }
}
