package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Expense;

import java.util.List;

public interface ExpenseDao {
    void save(Expense expense);
    void update(Expense expense);
    void deleteById(long id);
    Expense findById(long id);
    List<Expense> findAllByUserUsername(String username);
}
