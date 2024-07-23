package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Income;

import java.util.List;

public interface IncomeDao {
    void save(Income income);
    void update(Income income);
    void deleteById(long id);
    Income findById(long id);
    List<Income> findAllByUserUsername(String username);
}
