package com.ibex.pms.domain;

import com.ibex.pms.enums.OfferAcceptance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    private Property property;
    @ManyToOne
    private User buyer;
    private double buyerProposedPrice;
    @Enumerated(EnumType.STRING)
    private OfferAcceptance acceptance;
}
