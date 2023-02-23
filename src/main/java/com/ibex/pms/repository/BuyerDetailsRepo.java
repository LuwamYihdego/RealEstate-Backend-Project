package com.ibex.pms.repository;

import com.ibex.pms.domain.BuyerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerDetailsRepo extends JpaRepository<BuyerDetails, Long> {
}