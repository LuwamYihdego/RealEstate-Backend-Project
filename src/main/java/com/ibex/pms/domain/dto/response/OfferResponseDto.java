package com.ibex.pms.domain.dto.response;

import com.ibex.pms.enums.OfferAcceptance;
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
