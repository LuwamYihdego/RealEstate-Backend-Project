package com.ibex.pms.service.Impl;

import com.ibex.pms.domain.dto.request.LoginRequest;
import com.ibex.pms.domain.dto.request.RefreshTokenRequest;
import com.ibex.pms.domain.dto.response.JwtResponse;
import com.ibex.pms.domain.dto.response.LoginResponse;
import com.ibex.pms.domain.dto.response.UserResponseDto;
import com.ibex.pms.service.AuthService;
import com.ibex.pms.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
            System.out.println(result);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        var jwtResponse = new JwtResponse(accessToken, refreshToken);

        var user = new UserResponseDto(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getPhoneNumber(),
                userDetails.isAccountNonLocked(),
                userDetails.getRole()
        );

        var loginResponse = new LoginResponse(jwtResponse, user);
        return loginResponse;
    }

    @Override
    public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            // TODO (check the expiration of the accessToken when request sent, if the is recent according to
            //  issue Date, then accept the renewal)
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired)
                System.out.println("ACCESS TOKEN IS EXPIRED"); // TODO Renew is this case
            else
                System.out.println("ACCESS TOKEN IS NOT EXPIRED");
            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new JwtResponse(accessToken, refreshTokenRequest.getRefreshToken());
            // TODO (OPTIONAL) When to renew the refresh token?
            return loginResponse;
        }
        return new JwtResponse();
    }

}
