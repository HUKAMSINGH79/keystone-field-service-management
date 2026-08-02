package com.zidio.keystone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "Customer name is required")
    @Size(max = 150)
    private String customerName;
    @Size(max = 150)
    private String companyName;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9+ -]{7,20}$", message = "Phone number must be valid")
    private String phone;
    @NotBlank(message = "Address is required")
    @Size(max = 500)
    private String address;

}
