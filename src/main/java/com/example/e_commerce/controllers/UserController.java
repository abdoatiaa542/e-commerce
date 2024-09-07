package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.UserDto;
import com.example.e_commerce.models.entity.User;
import com.example.e_commerce.models.mappers.UserMapper;
import com.example.e_commerce.security.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;



    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        System.out.println("aeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        System.out.println("Refresh Token Request: " + refreshTokenRequest.getRefreshToken());
        String refreshToken = refreshTokenRequest.getRefreshToken();
        System.out.println("Refresh Token: " + refreshToken);
        User user = (User) userDetailsService.loadUserByUsername(jwtUtil.extractEmail(refreshToken));
        System.out.println("User: " + user.getEmail());

        if (jwtUtil.validateToken(refreshToken, user)) {
            String newAccessToken = jwtUtil.generateAccessToken(user);
            return new RefreshTokenResponse(newAccessToken);
        } else {
            return new RefreshTokenResponse("Invalid Token");
        }
    }



    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("Password: " + authenticationRequest.getPassword());
        System.out.println("Email: " + authenticationRequest.getEmail());


        User userDetails = (User) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        UserDto userDto = userMapper.toDto(userDetails);

        System.out.println("UserDetails: " + userDetails.getUsername());
        System.out.println("UserDto: " + userDto.getUsername());

        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        System.out.println("accesstoken: " + accessToken);
        System.out.println("refreshToken: " + refreshToken);
        return new AuthenticationResponse(accessToken, refreshToken, userDto);
    }

//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);




}
