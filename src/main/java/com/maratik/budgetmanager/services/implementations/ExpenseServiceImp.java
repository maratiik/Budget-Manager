package com.maratik.budgetmanager.services.implementations;

import com.maratik.budgetmanager.dao.interfaces.ExpenseDao;
import com.maratik.budgetmanager.entities.Expense;
import com.maratik.budgetmanager.services.interfaces.ExpenseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ExpenseServiceImp implements ExpenseService {
    private final ExpenseDao expenseDao;

    public ExpenseServiceImp(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @Override
    public void save(Expense expense) {
        expenseDao.save(expense);
    }

    @Override
    public void update(Expense expense) {
        expenseDao.update(expense);
    }

    @Override
    public void deleteById(long id) {
        expenseDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Expense> findById(long id) {
        return Optional.ofNullable(expenseDao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Expense> findAllByUserUsername(String username) {
        return expenseDao.findAllByUserUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> findExpenseSumsByUserUsernameAndMonthAndYear(String username, int month, int year) {
        Map<String, Long> sums = new HashMap<>();
        List<Expense> expenses = expenseDao.findAllByUserUsernameAndMonthAndYear(username, month, year);

        for (Expense expense : expenses) {
            switch (expense.getType()) {
                case ExpenseService.NEEDS -> sums.put(
                        ExpenseService.NEEDS, sums.get(ExpenseService.NEEDS) + expense.getValue());
                case ExpenseService.WANTS -> sums.put(
                        ExpenseService.WANTS, sums.get(ExpenseService.WANTS) + expense.getValue());
                case ExpenseService.SAVE -> sums.put(
                        ExpenseService.SAVE, sums.get(ExpenseService.SAVE) + expense.getValue());
            }
        }

        return sums;
    }
}
