package com.maratik.budgetmanager.exception_messages;

public abstract class DataExceptionMessage {
    public static final String USER_NOT_FOUND = "User not found";
    public static final String EXPENSE_NOT_FOUND = "Expense not found";
    public static final String INCOME_NOT_FOUND = "Income not found";
    public static final String GOAL_NOT_FOUND = "Goal not found";
    public static final String PLAN_NOT_FOUND = "Plan not found";

    public static final String CANNOT_SAVE_PREDICTED_PLAN = "Cannot save predicted plan";
    public static final String CANNOT_PREDICT_PLAN = "Cannot predict plan. Be sure to input expenses";
    public static final String CANNOT_GET_GOAL_DATE = "Cannot get goal date";

    public static final String PLAN_VIOLATED = "Your expenses are above your plan!";
}
