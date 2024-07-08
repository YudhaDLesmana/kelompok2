package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.Role;
import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.UserRepository;
import com.enigma.kelompok.security.JwtTokenProvider;
import com.enigma.kelompok.service.AuthService;
import com.enigma.kelompok.utils.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(UserDTO request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        //save session
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtTokenProvider.generateToken(auth);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);

        try {
            return response.get("token");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }
    }

    @Override
    public User register(UserDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBalance(request.getBalance());

        if (user.getRole() == null){
            user.setRole(Role.ROLE_USER);
        }
        return userRepository.save(user);
    }

    @PostConstruct
    public void initAdmin(){
        String username = "admin";
        String password = "admin";

        Optional<User> optionalUserCredential = userRepository.findByUsername(username);
        if (optionalUserCredential.isPresent()){
            return;
        }

        Role role = Role.ROLE_ADMIN;
        User userCredential = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role)
                .build();
        userRepository.save(userCredential);
    }
}
