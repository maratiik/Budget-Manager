package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Expense;
import com.maratik.budgetmanager.entities.Plan;

import java.sql.Date;

public interface FeatureService {
    /**
     * Predicts plan for user based on previous expenses
     * @return Plan
     */
    Plan predictPlan();

    /**
     * Save predicted plan
     */
    void savePredictedPlan();

    /**
     * Gets a date when the goal will be achieved
     * @param id id of the goal
     * @return Date
     */
    Date getGoalAchievedDateById(long id);

    /**
     * Gets a date when all the user's goals will be achieved
     * @param username username
     * @return Date
     */
    Date getAllGoalsAchievedDateByUserUsername(String username);

    /**
     * Checks whether the expense violates the user's plan
     * @param expense expense
     * @return true - if the expense is within plan's limits
     * false - if the expense is out of bounds of user's plan
     */
    boolean validateExpense(Expense expense);
}
