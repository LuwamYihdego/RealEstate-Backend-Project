package com.ibex.pms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {

    private double price;
    private int lotSize;
    private int numberOfBedRooms;
    private int numberOfBaths;
    private StatusDto status;
    private AddressDto address;
}
