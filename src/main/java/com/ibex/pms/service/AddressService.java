package com.ibex.pms.service;

import com.ibex.pms.domain.Address;
import java.util.List;

public interface AddressService {
    List<Address> getAll();
    Address getById(long id);
    void deleteById(long id);
    void  save(Address address);
    void  update(long id, Address address);
}
