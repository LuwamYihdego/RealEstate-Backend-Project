package com.ibex.pms.service;

import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.dto.PropertyDto;

import java.util.List;

public interface PropertyService {


     List<PropertyDto> getAllProperty();

    PropertyDto getPropertyById(long id);

    void deletePropertyById(long id);

    void  saveProperty(Property property);

     void updatePropertyById(Property property, long id);

    public void updatePropertyByUserId(Property property, long userId);

    List<PropertyDto> getPropertyByCriteria(double price, int lotSize, int numberOfBedRooms, int numberOfBaths);



}
