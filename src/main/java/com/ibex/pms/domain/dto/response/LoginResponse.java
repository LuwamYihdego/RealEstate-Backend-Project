package com.ibex.pms.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private JwtResponse jwtResponse;
    private UserResponseDto userResponseDto;
}
