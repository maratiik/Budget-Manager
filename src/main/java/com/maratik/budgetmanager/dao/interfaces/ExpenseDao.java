package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Expense;

import java.util.List;

public interface ExpenseDao {
    void save(Expense expense);
    void update(Expense expense);
    void deleteById(long id);
    Expense findById(long id);
    List<Expense> findAllByUserUsername(String username);
    List<Expense> findAllByUserUsernameAndMonthAndYear(String username, int month, int year);

    /**
     * Checks whether an expense with ID belongs to user with username
     * @param id Expense id that needs to be checked
     * @param username User username whose authority is being checked
     * @return 1 - Expense with ID belongs to User;
     * 0 - Expense with ID doesn't belong to User;
     * -1 - Expense with ID doesn't exist
     */
    int existsByIdAndUserUsername(long id, String username);
}
