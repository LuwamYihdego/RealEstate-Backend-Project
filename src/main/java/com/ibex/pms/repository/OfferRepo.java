package com.ibex.pms.repository;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
}