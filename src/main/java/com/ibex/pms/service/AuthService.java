package com.ibex.pms.service;

import com.ibex.pms.domain.dto.request.LoginRequest;
import com.ibex.pms.domain.dto.request.RefreshTokenRequest;
import com.ibex.pms.domain.dto.response.JwtResponse;
import com.ibex.pms.domain.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
