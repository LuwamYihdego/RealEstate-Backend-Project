package com.ibex.pms.domain.dto.request;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private Role role;
    private boolean isActive;
}
