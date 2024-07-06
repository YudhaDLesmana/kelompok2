package com.enigma.kelompok.service;

import com.enigma.kelompok.model.User;
import com.enigma.kelompok.utils.dto.UserDTO;

public interface AuthService {
    public String login(UserDTO request);
    User register(UserDTO request);
}
