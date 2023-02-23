package com.ibex.pms.controller;

import com.ibex.pms.domain.dto.request.OfferRequestDto;
import com.ibex.pms.domain.dto.response.OfferResponseDto;
import com.ibex.pms.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/offers")
@CrossOrigin("*")
public class OfferController {
    OfferService service;
    public OfferController(OfferService service){
        this.service = service;
    }
    @GetMapping
    public List<OfferResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OfferResponseDto getById(@PathVariable long id){
        return  service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id ){
        service.deleteById(id);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody OfferRequestDto offer, @PathVariable long id){
        service.update(id, offer);
    }
    @PostMapping()
    public void save(@RequestBody OfferRequestDto offer){
        service.save(offer);
    }
}
