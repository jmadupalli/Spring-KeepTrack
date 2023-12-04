package com.jay.springkeeptrack.controllers;

import com.jay.springkeeptrack.controllers.dto.AuthResponse;
import com.jay.springkeeptrack.controllers.dto.ErrorResponse;
import com.jay.springkeeptrack.controllers.dto.LoginRequest;
import com.jay.springkeeptrack.controllers.dto.RegisterRequest;
import com.jay.springkeeptrack.entity.user.User;
import com.jay.springkeeptrack.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @GetMapping("/")
    public ErrorResponse sayHello() {
        return new ErrorResponse("Hello from the ToDO Rest_API", 200);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(),
                loginRequest.getPassword());
        Authentication authResponse = this.authenticationManager.authenticate(authRequest);
        User user = userService.retriveUserByEmail(authResponse.getName());
        return new AuthResponse(user.getFirstName());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception{
        userService.createUser(registerRequest);
        return ResponseEntity.ok("Created :)");
    }
}
