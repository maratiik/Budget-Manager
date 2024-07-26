package com.maratik.budgetmanager.dao.interfaces;

import com.maratik.budgetmanager.entities.Income;

import java.util.List;

public interface IncomeDao {
    void save(Income income);
    void update(Income income);
    void deleteById(long id);
    Income findById(long id);
    List<Income> findAllByUserUsername(String username);

    /**
     * Checks whether an income with ID belongs to user with username
     * @param id Income id that needs to be checked
     * @param username User username whose authority is being checked
     * @return 1 - Income with ID belongs to User;
     * 0 - Income with ID doesn't belong to User;
     * -1 - Income with ID doesn't exist
     */
    int existsByIdAndUserUsername(long id, String username);
}
