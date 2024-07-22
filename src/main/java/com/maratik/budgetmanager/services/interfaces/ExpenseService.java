package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Expense;

import java.util.List;

public interface ExpenseService {
    void save(Expense expense);
    void update(Expense expense);
    void deleteById(long id);
    Expense findById(long id);
    List<Expense> findAllByUserUsername(String username);

    /**
     * Finds expense sums by types (needs, wants, save) in respected order.
     * Month is user as parameter. If month == 0, it counts as the last month.
     * @param username username
     * @param month month to find
     * @return ArrayList of expense sums of three types
     */
    List<Long> findExpenseSumsByUserUsernameAndMonth(String username, int month);

}
