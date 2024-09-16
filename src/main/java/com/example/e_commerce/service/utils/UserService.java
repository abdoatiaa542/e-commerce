package com.example.e_commerce.service.utils;

import com.example.e_commerce.models.dto.RegisterDtoRequest;
import com.example.e_commerce.models.entity.User;


public interface UserService {

    String register(RegisterDtoRequest registerDtoRequest);

    User updateUser(User user);

    User deleteUser(Integer id);

    User getUser(Integer id);
}
