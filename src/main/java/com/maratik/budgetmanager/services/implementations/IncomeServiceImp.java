package com.maratik.budgetmanager.services.implementations;

import com.maratik.budgetmanager.dao.interfaces.IncomeDao;
import com.maratik.budgetmanager.entities.Income;
import com.maratik.budgetmanager.services.interfaces.IncomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IncomeServiceImp implements IncomeService {
    private final IncomeDao incomeDao;

    public IncomeServiceImp(IncomeDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    @Override
    public void save(Income income) {
        incomeDao.save(income);
    }

    @Override
    public void update(Income income) {
        incomeDao.update(income);
    }

    @Override
    public void deleteById(long id) {
        incomeDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Income> findById(long id) {
        return Optional.ofNullable(incomeDao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Income> findAllByUserUsername(String username) {
        return incomeDao.findAllByUserUsername(username);
    }

    @Override
    public int existsByIdAndUserUsername(long id, String username) {
        return incomeDao.existsByIdAndUserUsername(id, username);
    }
}
