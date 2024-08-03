package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Income;

import java.util.List;
import java.util.Optional;

public interface IncomeService {
    void save(Income income);
    void update(Income income);
    void deleteById(long id);
    Optional<Income> findById(long id);
    List<Income> findAllByUserUsername(String username);
    int existsByIdAndUserUsername(long id, String username);
}
