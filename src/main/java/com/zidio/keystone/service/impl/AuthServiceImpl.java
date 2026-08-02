package com.zidio.keystone.service.impl;

import com.zidio.keystone.dto.AuthResponse;
import com.zidio.keystone.dto.LoginRequest;
import com.zidio.keystone.dto.RegisterRequest;
import com.zidio.keystone.entity.Role;
import com.zidio.keystone.entity.User;
import com.zidio.keystone.exception.DuplicateResourceException;
import com.zidio.keystone.exception.ResourceNotFoundException;
import com.zidio.keystone.repository.RoleRepository;
import com.zidio.keystone.repository.UserRepository;
import com.zidio.keystone.security.JwtService;
import com.zidio.keystone.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase(Locale.ROOT);
        String requestedRole = request.getRole().trim().toUpperCase(Locale.ROOT);
        if (!"TECHNICIAN".equals(requestedRole)) {
            throw new IllegalArgumentException("Public registration is available for technicians only");
        }
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("A user with this email already exists");
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber().trim())) {
            throw new DuplicateResourceException("A user with this phone number already exists");
        }
        Role role = roleRepository.findByRoleName(requestedRole)
                .orElseThrow(() -> new ResourceNotFoundException("Role", request.getRole()));
        User user = new User();
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber().trim());
        user.setActive(true);
        user.setRole(role);
        User savedUser = userRepository.save(user);
        return new AuthResponse(jwtService.generateToken(savedUser), "User registered successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail().trim().toLowerCase(Locale.ROOT), request.getPassword()));
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User", authentication.getName()));
        return new AuthResponse(jwtService.generateToken(user), "Login successful");
    }
}
