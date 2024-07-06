package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.UserRepository;
import com.enigma.kelompok.service.UserService;
import com.enigma.kelompok.utils.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> getAll(String username, String password, Integer balance, Pageable pageable) {
        Specification<User> specification = UserSpecification.getSpecification(username, password, balance);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User Not Found")
                );
    }

    @Override
    public User create(User request) {
        return userRepository.save(request);
    }

    @Override
    public User update(Integer id, User request) {
        User user = this.getOne(id);
        user.setUsername(request.getUsername());
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

}
