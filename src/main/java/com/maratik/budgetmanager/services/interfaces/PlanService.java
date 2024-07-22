package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Plan;

import java.util.List;

public interface PlanService {
    void save(Plan plan);
    void update(Plan plan);
    void deleteById(long id);
    Plan findById(long id);
    List<Plan> findAllByUserUsername(String username);

    /**
     * Saves recommended plan (50% - needs, 30% - wants, 20% - save)
     */
    void saveRecommendedPlan();
}
