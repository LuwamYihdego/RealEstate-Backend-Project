package com.ibex.pms.domain.dto.request;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Offer;
import com.ibex.pms.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequestDto {
    private long id;
    private double price;
    private int lotSize;
    private String propertyNumber;
    private String description;
    private int numberOfBedRooms;
    private int numberOfBaths;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Address address;
    private long sellerId;
}