package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.User;

public interface UserService {
    void save(User user);
    void update(User user);
    void deleteById(long id);
    User findById(long id);
}
