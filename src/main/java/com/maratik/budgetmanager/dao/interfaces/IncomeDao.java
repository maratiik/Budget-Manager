package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Income;

public interface IncomeDao {
    void save(Income income);
    void update(Income income);
    void deleteById(long id);
    Income findByUserUsername(String username);
}
