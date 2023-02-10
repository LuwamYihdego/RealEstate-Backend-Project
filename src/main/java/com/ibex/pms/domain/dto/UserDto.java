package com.ibex.pms.domain.dto;

import com.ibex.pms.domain.Address;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
}
