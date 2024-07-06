package com.enigma.kelompok.service;

import com.enigma.kelompok.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getAll(String username, String password, Integer balance, Pageable pageable);

    User getOne(Integer id);
    User create(User request);
    User update(Integer id, User request);
    void delete(Integer id);

}
