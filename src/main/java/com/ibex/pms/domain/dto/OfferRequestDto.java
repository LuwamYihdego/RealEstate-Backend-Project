package com.ibex.pms.domain.dto;

import com.ibex.pms.domain.Property;
import com.ibex.pms.domain.User;
import com.ibex.pms.enums.OfferAcceptance;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequestDto {
    private long id;
    private long propertyId;
    private long buyerId;
    private double buyerProposedPrice;
    @Enumerated(EnumType.STRING)
    private OfferAcceptance acceptance;
}
