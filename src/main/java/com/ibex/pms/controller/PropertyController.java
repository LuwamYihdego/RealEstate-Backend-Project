package com.ibex.pms.controller;

import com.ibex.pms.domain.Property;
import com.ibex.pms.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/properties")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties(){
        return propertyService.getAllProperty();
    }
    @GetMapping("/{id}")
    public Property getProperty(@PathVariable long id){
        return  propertyService.getPropertyById(id);
    }
    @DeleteMapping("/{id}")
    public void deletePropertyById(@PathVariable long id ){
        propertyService.deletePropertyById(id);
    }
    @PutMapping("/{id}")
    public void updatePropertyById(@RequestBody Property property, @PathVariable long id){

        propertyService.updatePropertyById(property, id);

    }
    @PostMapping()
    public void createProperty(@RequestBody Property property){

        propertyService.saveProperty(property);

    }

   @PostMapping("/{userId}")
    public void addPropertyByUserId(@RequestBody Property property, @PathVariable long userId){
        propertyService.updatePropertyByUserId(property, userId);
    }




}
