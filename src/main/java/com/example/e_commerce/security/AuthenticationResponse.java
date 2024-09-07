package com.example.e_commerce.security;


import com.example.e_commerce.models.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationResponse {

    private final String accessToken;
    private final String refreshToken;
    private UserDto userDto;

    public AuthenticationResponse(String accessToken, String refreshToken, UserDto userDto) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userDto = userDto;
    }


}
