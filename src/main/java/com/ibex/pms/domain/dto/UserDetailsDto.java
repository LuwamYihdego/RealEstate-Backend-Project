package com.ibex.pms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
