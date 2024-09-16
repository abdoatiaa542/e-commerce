package com.example.e_commerce.controllers;


import com.example.e_commerce.models.dto.RegisterDtoRequest;
import com.example.e_commerce.models.dto.UserDto;
import com.example.e_commerce.models.entity.User;
import com.example.e_commerce.models.mappers.UserMapper;
import com.example.e_commerce.security.*;
import com.example.e_commerce.service.utils.OTPService;
import com.example.e_commerce.service.utils.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OTPService otpService;


    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        User user = (User) userDetailsService.loadUserByUsername(jwtUtil.extractEmail(refreshToken));

        if (jwtUtil.validateToken(refreshToken, user)) {
            String newAccessToken = jwtUtil.generateAccessToken(user);
            return new RefreshTokenResponse(newAccessToken);
        } else {
            return new RefreshTokenResponse("Invalid Token");
        }
    }


    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        User userDetails = (User) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        UserDto userDto = userMapper.toDto(userDetails);
        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        return new AuthenticationResponse(accessToken, refreshToken, userDto);
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDtoRequest registerRequest) {
        String response = userService.register(registerRequest);
        if (response.equals("Email already exists")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        User userEntity = userMapper.toEntity(userDto);
        userEntity.setId(userId);
        User user = userService.updateUser(userEntity);
        return ResponseEntity.ok(userMapper.toDto(user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Integer id) {
        User user = userService.deleteUser(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(userMapper.toDto(user));
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendOTP(@RequestParam String phoneNumber) {
        System.out.println("Phone number: " + phoneNumber);
        try {
            otpService.sendOTP(phoneNumber);
            return ResponseEntity.ok("OTP sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send OTP: " + e.getMessage());
        }
    }


    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestParam String phoneNumber, @RequestParam String otp) {
        boolean isValid = otpService.verifyOTP(phoneNumber, otp);
        if (isValid) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }



}
