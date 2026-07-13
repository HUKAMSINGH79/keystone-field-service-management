package com.zidio.keystone.service;

import com.zidio.keystone.dto.AuthResponse;
import com.zidio.keystone.dto.LoginRequest;
import com.zidio.keystone.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
