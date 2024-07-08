package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.UserRepository;
import com.enigma.kelompok.service.UserService;

import com.enigma.kelompok.utils.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> getAll(String username, Integer balance, Pageable pageable) {
        Specification<User> specification = UserSpecification.getUserSpecification(username, balance);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
    }

    @Override
    public User update(Integer id, User user) {
        User userUpdated = this.getOne(id);
        userUpdated.setUsername(user.getUsername());
        userUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        userUpdated.setBalance(user.getBalance());
        userRepository.save(userUpdated);
        return userUpdated;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
