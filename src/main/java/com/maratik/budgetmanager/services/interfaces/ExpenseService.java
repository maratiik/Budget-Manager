package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Expense;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExpenseService {
    static String NEEDS = "needs";
    static String WANTS = "wants";
    static String SAVE = "save";

    void save(Expense expense);
    void update(Expense expense);
    void deleteById(long id);
    Optional<Expense> findById(long id);
    List<Expense> findAllByUserUsername(String username);

    /**
     * Finds expense sums by types (needs, wants, save) in respected order.
     * Month is user as parameter. If month == 0, it counts as the last month.
     * @param username username
     * @param month month to find
     * @return Map of expense sums of three types: NEEDS, WANTS, SAVE
     */
    Map<String, Long> findExpenseSumsByUserUsernameAndMonthAndYear(String username, int month, int year);
}
