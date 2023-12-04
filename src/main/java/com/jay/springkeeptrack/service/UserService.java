package com.jay.springkeeptrack.service;

import com.jay.springkeeptrack.controllers.dto.RegisterRequest;
import com.jay.springkeeptrack.entity.user.Role;
import com.jay.springkeeptrack.entity.user.User;
import com.jay.springkeeptrack.entity.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(RegisterRequest request) throws Exception{
        Optional<User> user= userRepository.findByEmail(request.getUsername());
        System.out.println(request.getUsername());
        if(user.isPresent())
            throw new Exception("Provided email is already registered!");
        User newUser = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getUsername())
                        .role(Role.USER)
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build();
        userRepository.save(newUser);
    }

    public User retriveUserByEmail(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("Invalid Credentials");
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), user.get().getAuthorities());
    }
}
