package com.ibex.pms.controller;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;
import com.ibex.pms.service.AddressService;
import com.ibex.pms.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoleController {
    RoleService service;
    public RoleController(RoleService service){
        this.service = service;
    }

    @GetMapping
    public List<Role> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable long id){
        return  service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id ){
        service.deleteById(id);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody Role role, @PathVariable long id){
        service.update(id, role);
    }
    @PostMapping()
    public void save(@RequestBody Role role){
        service.save(role);
    }
}
