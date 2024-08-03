package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    void save(Plan plan);
    void update(Plan plan);
    void deleteById(long id);
    Optional<Plan> findById(long id);
    Optional<Plan> findByUserUsername(String username);
    int existsByIdAndUserUsername(long id, String username);
}
