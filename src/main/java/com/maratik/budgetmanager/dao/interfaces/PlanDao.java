package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Plan;

import java.util.List;

public interface PlanDao {
    void save(Plan plan);
    void update(Plan plan);
    void deleteById(long id);
    Plan findById(long id);
    Plan findByUserUsername(String username);
}
