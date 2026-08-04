package com.zidio.keystone.service;

import com.zidio.keystone.dto.AuthResponse;
import com.zidio.keystone.dto.ForgotPasswordRequest;
import com.zidio.keystone.dto.LoginRequest;
import com.zidio.keystone.dto.RegisterRequest;
import com.zidio.keystone.dto.request.ResetPasswordRequest;

import java.util.List;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    void forgotPassword(ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);

}
