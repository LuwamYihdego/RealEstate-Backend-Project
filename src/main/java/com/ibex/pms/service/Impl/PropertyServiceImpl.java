package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Offer;
import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.OfferResponseDto;
import com.ibex.pms.domain.dto.PropertyRequestDto;
import com.ibex.pms.domain.dto.PropertyResponseDto;
import com.ibex.pms.repository.*;
import com.ibex.pms.service.PropertyService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PropertyServiceImpl implements PropertyService {
    PropertyRepo propertyRepo;
    @PersistenceContext
    EntityManager em;
    private UserRepo userRepo;
    private ModelMapper mapper;

    private PropertySearchDao propertySearchDao;
    private final AddressRepo addressRepo;
    private final OfferRepo offerRepo;

    @Autowired
    public PropertyServiceImpl(PropertyRepo propertyRepo,
                               UserRepo userRepo,
                               ModelMapper mapper,
                               PropertySearchDao propertySearchDao,
                               AddressRepo addressRepo,
                               OfferRepo offerRepo) {
        this.propertyRepo = propertyRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.propertySearchDao = propertySearchDao;
        this.addressRepo = addressRepo;
        this.offerRepo = offerRepo;
    }

    @Override
    public List<OfferResponseDto> getAllOffers(long id){
        List<Offer> list = new ArrayList<>();
        offerRepo.findAll().stream().filter(f -> f.getProperty().getId() == id).forEach(list::add);
        return list
                .stream()
                .map(p -> mapper.map(p, OfferResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyResponseDto> getAll() {
        List<Property> list = new ArrayList<>();
        propertyRepo.findAll().forEach(list::add);
        return list
                .stream()
                .map(p -> mapper.map(p, PropertyResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PropertyResponseDto getById(long id) {
        Property property = propertyRepo.findById(id).orElse(null);
        return mapper.map(property, PropertyResponseDto.class);
    }

    @Override
    public void deleteById(long id) {
        propertyRepo.deleteById(id);
    }

    @Override
    public void save(PropertyRequestDto propertyDto) {
        Property property = mapper.map(propertyDto, Property.class);
        Address propertyLocation = property.getAddress();
        if (propertyLocation != null && propertyLocation.getId() == 0) {
            Address address = addressRepo.getAddressByStreetEqualsIgnoreCase(property.getAddress().getStreet());
            if (address != null)
                property.setAddress(address);
            else
                addressRepo.save(property.getAddress());
        }

        long userId = propertyDto.getSellerId();
        if (userId != 0) {
            User user = userRepo.findById(userId).get();
            if (user != null)
                property.setSeller(user);
        }
        propertyRepo.save(property);
    }

    @Override
    public void update(PropertyRequestDto property, long id) {
        var prop = propertyRepo.findById(id).get();
        prop.setAddress(property.getAddress());
        prop.setDescription(property.getDescription());
        prop.setPropertyNumber(property.getPropertyNumber());
        prop.setPrice(property.getPrice());
        prop.setStatus(property.getStatus());
        prop.setLotSize(property.getLotSize());
        prop.setNumberOfBaths(property.getNumberOfBaths());
        prop.setNumberOfBedRooms(property.getNumberOfBedRooms());
        prop.setSeller(userRepo.getById(property.getSellerId()));
        em.persist(prop);

        Address address = addressRepo.findById(property.getAddress().getId()).get();
        address.setCity(property.getAddress().getCity());
        address.setStreet(property.getAddress().getStreet());
        address.setState(property.getAddress().getState());
        address.setZipCode(property.getAddress().getZipCode());
        em.persist(address);
    }

//    @Override
//    public void updateByUserId(PropertyDto property, long userId) {
//        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Property not found with id:" + userId));
//        List<Property> prop = user.getPropertyList();
//        prop.add(property);
//        em.persist(user);
//    }

    @Override
    public List<PropertyResponseDto> getByCriteria(double price, int lotSize, int numberOfBedRooms, int numberOfBaths) {
        List<Property> prop = propertySearchDao.findAllBySimpleQuery(price, lotSize, numberOfBedRooms, numberOfBaths);
        List<PropertyResponseDto> propDto = Arrays.asList(mapper.map(prop, PropertyResponseDto[].class));
        return propDto;
    }
}