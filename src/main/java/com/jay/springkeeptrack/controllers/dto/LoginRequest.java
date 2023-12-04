package com.jay.springkeeptrack.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Invalid Credentials")
    private String username;

    @Size(min=8, message = "Invalid Credentials")
    private String password;
}
