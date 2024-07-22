package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Goal;

import java.util.List;

public interface GoalDao {
    void save(Goal goal);
    void update(Goal goal);
    void deleteById(long id);
    Goal findById(long id);
    List<Goal> findAllByUserUsername(String username);
}
