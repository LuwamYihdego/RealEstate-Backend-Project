package com.ibex.pms.controller;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Offer;
import com.ibex.pms.service.AddressService;
import com.ibex.pms.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/offers")
public class OfferController {
    OfferService service;
    public OfferController(OfferService service){
        this.service = service;
    }
    @GetMapping
    public List<Offer> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Offer getById(@PathVariable long id){
        return  service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id ){
        service.deleteById(id);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Offer offer, @PathVariable long id){
        service.update(id, offer);
    }
    @PostMapping()
    public void save(@RequestBody Offer offer){
        service.save(offer);
    }
}
