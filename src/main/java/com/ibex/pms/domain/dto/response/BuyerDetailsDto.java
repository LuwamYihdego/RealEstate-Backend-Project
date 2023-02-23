package com.ibex.pms.domain.dto.response;

import com.ibex.pms.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerDetailsDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
}
