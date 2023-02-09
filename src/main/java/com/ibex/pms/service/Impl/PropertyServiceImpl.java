package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.User;
import com.ibex.pms.domain.dto.PropertyDto;
import com.ibex.pms.exceptions.ResourceNotFoundException;
import com.ibex.pms.repository.PropertyRepo;
import com.ibex.pms.repository.PropertySearchDao;
//import com.ibex.pms.repository.UserDetailsRepo;
import com.ibex.pms.repository.UserRepo;
import com.ibex.pms.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    PropertyRepo propertyRepo;


    //@Autowired
    private UserRepo userRepo;
    //@Autowired
    private ModelMapper mapper;

    @Autowired
    public PropertyServiceImpl(PropertyRepo propertyRepo,
                               //UserDetailsRepo userDetailsRepo,
                               UserRepo userRepo,
                               ModelMapper mapper){
        this.propertyRepo = propertyRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Autowired
    private PropertySearchDao propertySearchDao;


    @Override
    public List<PropertyDto> getAllProperty() {

        List<Property> prop = propertyRepo.findAll();
        List<PropertyDto> propDtoo = Arrays.asList(mapper.map(prop, PropertyDto[].class));

        return propDtoo;
    }

    @Override
    public PropertyDto getPropertyById(long id) {
        Property prop = propertyRepo.findById(id).orElse(null);
        PropertyDto propDto = mapper.map(prop, PropertyDto.class);
        return propDto;
    }

    @Override
    public void deletePropertyById(long id) {

        propertyRepo.deleteById(id);

    }

    @Override
    public void saveProperty(Property property) {

        propertyRepo.save(property);

    }

    @Override
    public void updatePropertyById(Property property, long id) {

        var prop = propertyRepo.findById(id).get();
        prop.setAddress(property.getAddress());
        prop.setDescription(property.getDescription());
        prop.setPrice(property.getPrice());
        prop.setStatus(property.getStatus());
        prop.setLotSize(property.getLotSize());
        prop.setNumberOfBaths(property.getNumberOfBaths());
        prop.setNumberOfBedRooms(property.getNumberOfBedRooms());
    }

    @Override
    public void updatePropertyByUserId(Property property, long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Property not found with id:" + userId));
        List<Property> prop = user.getPropertyList();
        prop.add(property);
    }

    @Override
    public List<PropertyDto> getPropertyByCriteria(double price, int lotSize, int numberOfBedRooms, int numberOfBaths) {

        List<Property> prop = propertySearchDao.findAllBySimpleQuery(price, lotSize, numberOfBedRooms, numberOfBaths);
        List<PropertyDto> propDto = Arrays.asList(mapper.map(prop, PropertyDto[].class));
        return propDto;
    }
}
