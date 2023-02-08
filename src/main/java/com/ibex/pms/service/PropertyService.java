package com.ibex.pms.service;

import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.UserDetails;

import java.util.List;

public interface PropertyService {


    List<Property> getAllProperty();

    Property getPropertyById(long id);

    void deletePropertyById(long id);

    void  saveProperty(Property property);

     void updatePropertyById(Property property, long id);

    public void updatePropertyByUserId(Property property, long userId);


}
