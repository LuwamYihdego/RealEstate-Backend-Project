package com.ibex.pms.controller;

import com.ibex.pms.domain.Address;
import com.ibex.pms.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/addresses")
@CrossOrigin("*")
public class AddressController {
    AddressService service;
    public AddressController(AddressService service){
        this.service = service;
    }
    @GetMapping
    public List<Address> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable long id){
        return  service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id ){
        service.deleteById(id);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Address address, @PathVariable long id){
        service.update(id, address);
    }
    @PostMapping()
    public void save(@RequestBody Address address){
        service.save(address);
    }
}
