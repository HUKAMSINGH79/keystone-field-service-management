package com.zidio.keystone.service.impl;

import com.zidio.keystone.dto.AuthResponse;
import com.zidio.keystone.dto.LoginRequest;
import com.zidio.keystone.dto.RegisterRequest;
import com.zidio.keystone.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse register(RegisterRequest request) {

        //Business logic will be implemented later
        return new AuthResponse(null,"User Registered Successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // Business logic will be implemented later
        return new AuthResponse(null,"Login Successful");
    }
}
