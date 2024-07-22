package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Income;

public interface IncomeService {
    void save(Income income);
    void update(Income income);
    void deleteById(long id);
    Income findByUserUsername(String username);
}
