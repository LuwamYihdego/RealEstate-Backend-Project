package com.ibex.pms.domain;

import com.ibex.pms.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private int lotSize;
    private String description;
    private int numberOfBedRooms;
    private int numberOfBaths;

    @OneToMany(mappedBy = "property")
    private List<Offer> offers;
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private User seller;
}