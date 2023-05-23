package com.joseph.standardwebproject.controller;

import com.joseph.standardwebproject.common.request.JwtTokenRequest;
import com.joseph.standardwebproject.common.response.JwtTokenResponse;
import com.joseph.standardwebproject.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest){
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(),
                        jwtTokenRequest.password()
                );
        var authentication = authenticationManager.authenticate(authenticationToken);

        var token = jwtTokenService.generateToken(authentication);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }
}
