package com.jay.springkeeptrack.controllers;

import com.jay.springkeeptrack.controllers.dto.LoginRequest;
import com.jay.springkeeptrack.controllers.dto.RegisterRequest;
import com.jay.springkeeptrack.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @GetMapping("/")
    public ErrorResponse sayHello() {
        return new ErrorResponse("Hello from the ToDO Rest_API", 200);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(),
                loginRequest.getPassword());
        Authentication authResponse = this.authenticationManager.authenticate(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception{
        userService.createUser(registerRequest);
        return ResponseEntity.ok("Created :)");
    }
}
