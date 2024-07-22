package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.User;

public interface UserDao {
    void save(User user);
    void update(User user);
    void deleteById(long id);
    User findById(long id);
}
