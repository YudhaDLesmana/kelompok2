package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.User;
import com.enigma.kelompok.service.AuthService;
import com.enigma.kelompok.utils.dto.UserDTO;
import com.enigma.kelompok.utils.response.Res;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO request) {
        User user = authService.register(request);
        return Res.renderJson(user, "Successfully Registerd!", HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO request) {
        String token = authService.login(request);
        return Res.renderJson(token, "Successfully Login!", HttpStatus.ACCEPTED);
    }
}
