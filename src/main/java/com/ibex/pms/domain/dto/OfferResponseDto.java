package com.ibex.pms.domain.dto;

import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.User;
import com.ibex.pms.enums.OfferAcceptance;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferResponseDto {
    private long id;
    private BuyerDetailsDto buyer;
    private PropertyResponseDto property;
    private double buyerProposedPrice;
    private OfferAcceptance acceptance;
    private Date createdDate;
}
