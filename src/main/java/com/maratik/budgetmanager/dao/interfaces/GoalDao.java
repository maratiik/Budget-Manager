package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Goal;

import java.util.List;

public interface GoalDao {
    void save(Goal goal);
    void update(Goal goal);
    void deleteById(long id);
    Goal findById(long id);
    List<Goal> findAllByUserUsername(String username);

    /**
     * Checks whether a goal with ID belongs to user with username
     * @param id Goal id that needs to be checked
     * @param username User username whose authority is being checked
     * @return 1 - Goal with ID belongs to User;
     * 0 - Goal with ID doesn't belong to User;
     * -1 - Goal with ID doesn't exist
     */
    int existsByIdAndUserUsername(long id, String username);
}
