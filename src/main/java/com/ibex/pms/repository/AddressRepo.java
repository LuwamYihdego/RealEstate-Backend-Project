package com.ibex.pms.repository;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    Address getAddressByStreetEqualsIgnoreCase(String street);
}