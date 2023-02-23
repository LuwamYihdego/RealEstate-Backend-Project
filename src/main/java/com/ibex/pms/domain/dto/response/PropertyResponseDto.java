package com.ibex.pms.domain.dto.response;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.dto.request.UserRequestDto;
import com.ibex.pms.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponseDto {
    private long id;
    private double price;
    private int lotSize;
    private String propertyNumber;
    private String description;
    private int numberOfBedRooms;
    private int numberOfBaths;
    @Enumerated(EnumType.STRING)
    private Status status;
    //private List<Offer> offers;
    private Address address;
    private UserRequestDto seller;
    private Date createdDate;
}