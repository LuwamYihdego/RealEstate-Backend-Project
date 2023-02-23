package com.ibex.pms.domain.dto.response;

import com.ibex.pms.domain.Address;
import com.ibex.pms.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private Role role;
    private boolean isActive;

    public UserResponseDto(long id, String email, String firstName, String lastName, String phoneNumber, boolean isActive, Role role){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.role = role;
    }
}
