package com.ibex.pms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    /*
    * Unique identifier of the property
    * */
    private String propertyNumber;
    private String description;
    private int numberOfBedRooms;
    private int numberOfBaths;
    @OneToMany(mappedBy = "property")
    @JsonManagedReference
    private List<Offer> offers;
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User seller;
}