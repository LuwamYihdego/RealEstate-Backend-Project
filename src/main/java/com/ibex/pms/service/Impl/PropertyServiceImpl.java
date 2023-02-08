package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.UserDetails;
import com.ibex.pms.repository.PropertyRepo;
import com.ibex.pms.repository.UserDetailsRepo;
import com.ibex.pms.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    public List<Property> getAllProperty() {
        return  propertyRepo.findAll();
    }

    @Override
    public Property getPropertyById(long id) {
        return propertyRepo.findById(id).orElse(null);
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

        var prop  = propertyRepo.findById(id).get();
        prop.setAddress(property.getAddress());
        prop.setDescription(property.getDescription());
        prop.setPrice(property.getPrice());
        prop.setStatus(property.getStatus());
        prop.setLotSize(property.getLotSize());
        prop.setNumberOfBaths(property.getNumberOfBaths());
        prop.setNumberOfBedRooms(property.getNumberOfBedRooms());


    }
    @Override
    public void updatePropertyByUserId(Property property, long userId){

         UserDetails user = userDetailsRepo.findById(userId).orElse(null);

        List<Property> prop =  user.getPropertyList();

        prop.add(property);



    }

}
