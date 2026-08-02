package com.zidio.keystone.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    boolean active;
    String role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
