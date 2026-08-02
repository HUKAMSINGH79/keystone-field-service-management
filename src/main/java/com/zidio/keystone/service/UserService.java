package com.zidio.keystone.service;

import com.zidio.keystone.dto.UserResponse;
import com.zidio.keystone.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}
