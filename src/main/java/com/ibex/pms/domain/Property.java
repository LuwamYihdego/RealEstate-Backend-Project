package com.ibex.pms.domain;

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
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    private int lotSize;

    private String description;
    private int numberOfBedRooms;
    private int numberOfBaths;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;
    private Status status;
    @ManyToOne()
    @JoinColumn(name="userDetails_id")
    private UserDetails userDetails;




}

//
