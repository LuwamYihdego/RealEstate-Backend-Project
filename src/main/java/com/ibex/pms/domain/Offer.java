package com.ibex.pms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonBackReference
    private Property property;
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonBackReference
    private User buyer;
    private double buyerProposedPrice;
    @Enumerated(EnumType.STRING)
    private OfferAcceptance acceptance;
}
