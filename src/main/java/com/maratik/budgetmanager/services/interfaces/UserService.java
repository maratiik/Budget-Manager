package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    void update(User user);
    void deleteById(long id);
    Optional<User> findById(long id);
}
