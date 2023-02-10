package com.ibex.pms.repository;

import com.ibex.pms.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OfferRepo extends JpaRepository<Offer, Long> {
}