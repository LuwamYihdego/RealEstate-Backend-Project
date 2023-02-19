package com.ibex.pms.controller;

import com.ibex.pms.domain.dto.response.OfferResponseDto;
import com.ibex.pms.domain.dto.request.PropertyRequestDto;
import com.ibex.pms.domain.dto.response.PropertyResponseDto;
import com.ibex.pms.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/properties")
@CrossOrigin("*")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping
    public List<PropertyResponseDto> getAll() {
        return propertyService.getAll();
    }

    @GetMapping("/{id}/offers")
    public List<OfferResponseDto> getAllOffers(@PathVariable(name="id") Long id) {
        return propertyService.getAllOffers(id);
    }

    @GetMapping("/{id}")
    public PropertyResponseDto getById(@PathVariable(name="id") Long id) {
        return propertyService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name="id") long id) {
        propertyService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@RequestBody PropertyRequestDto property, @PathVariable long id) {
        propertyService.update(property, id);
    }

    @PostMapping()
    public void create(@RequestBody PropertyRequestDto property) {
        propertyService.save(property);
    }

//    @PostMapping("/{userId}")
//    public void addByUserId(@RequestBody Property property, @PathVariable long userId) {
//        propertyService.updateByUserId(property, userId);
//    }

    @GetMapping("/search")
    public List<PropertyResponseDto> getByCriteria(@RequestParam double price,
                                                   @RequestParam int lotSize,
                                                   @RequestParam int numberOfBedRooms,
                                                   @RequestParam int numberOfBaths) {
        return propertyService.getByCriteria(price, lotSize, numberOfBedRooms, numberOfBaths);
    }
}