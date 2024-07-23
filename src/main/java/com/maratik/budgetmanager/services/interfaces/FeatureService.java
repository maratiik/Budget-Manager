package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Expense;
import com.maratik.budgetmanager.entities.Plan;

import java.sql.Date;
import java.util.Optional;

public interface FeatureService {
    /**
     * Saves recommended plan (50% - needs, 30% - wants, 20% - save)
     */
    void saveRecommendedPlanByUsername(String username);

    /**
     * Predicts plan for user based on previous expenses
     * @return Plan
     */
    Optional<Plan> predictPlanByUsername(String username);

    /**
     * Save predicted plan
     */
    Optional<Plan> savePredictedPlanByUsername(String username);

    /**
     * Gets a date when the goal will be achieved
     * @param id id of the goal
     * @return Date
     */
    Optional<Date> getGoalAchievedDateById(long id);

    /**
     * Gets a date when all the user's goals will be achieved
     * @param username username
     * @return Date
     */
    Optional<Date> getAllGoalsAchievedDateByUserUsername(String username);

    /**
     * Checks whether the expense violates the user's plan
     * @param expense expense
     * @return true - if the expense is within plan's limits
     * false - if the expense is out of bounds of user's plan
     */
    boolean validateExpense(Expense expense);
}
