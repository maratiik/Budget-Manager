package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Plan;

import java.util.List;

public interface PlanDao {
    void save(Plan plan);
    void update(Plan plan);
    void deleteById(long id);
    Plan findById(long id);
    Plan findByUserUsername(String username);

    /**
     * Checks whether a plan with ID belongs to user with username
     * @param id Plan id that needs to be checked
     * @param username User username whose authority is being checked
     * @return 1 - Plan with ID belongs to User;
     * 0 - Plan with ID doesn't belong to User;
     * -1 - Plan with ID doesn't exist
     */
    int existsByIdAndUserUsername(long id, String username);
}
