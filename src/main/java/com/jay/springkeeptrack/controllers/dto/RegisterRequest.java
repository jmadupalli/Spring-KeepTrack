package com.jay.springkeeptrack.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "first name is mandatory")
    @Size(min=3, message="Invalid firstName")
    private String firstName;

    @NotBlank(message = "last name is mandatory")
    @Size(min=3, message = "Invalid lastName")
    private String lastName;

    @NotBlank(message = "email/username is mandatory")
    @Email(message = "Invalid Email")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min=8, message = "Password should have min 8 chars")
    private String password;
}

