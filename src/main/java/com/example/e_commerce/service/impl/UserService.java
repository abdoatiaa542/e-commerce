package com.example.e_commerce.service.impl;

import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.models.entity.User;
import com.example.e_commerce.models.entity.UserRole;
import com.example.e_commerce.reposatory.UserRepository;
import com.example.e_commerce.reposatory.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements com.example.e_commerce.service.utils.UserService {

    @Autowired
    private com.example.e_commerce.security.MyUserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;


    public String register(com.example.e_commerce.models.dto.RegisterDtoRequest registerDtoRequest) {

        try {
            userDetailsService.loadUserByUsername(registerDtoRequest.getEmail());
            return "Email already exists";
        } catch (UsernameNotFoundException e) {
            User newUser = new User();
            newUser.setUsername(registerDtoRequest.getUsername());
            newUser.setEmail(registerDtoRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(registerDtoRequest.getPassword()));


            UserRole defaultRole = userRoleRepository.findByRole("USER")   // !!
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            newUser.setRole(defaultRole);

            userRepository.save(newUser);
            return "User created successfully";
        }
    }


    @Override
    public User updateUser(User user) {

        userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userRepository.save(user);
    }


    @Override
    public User deleteUser(Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(existingUser);
        return existingUser;
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}