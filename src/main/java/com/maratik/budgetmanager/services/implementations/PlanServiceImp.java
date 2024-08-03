package com.maratik.budgetmanager.services.implementations;

import com.maratik.budgetmanager.dao.interfaces.PlanDao;
import com.maratik.budgetmanager.entities.Plan;
import com.maratik.budgetmanager.services.interfaces.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlanServiceImp implements PlanService {
    private final PlanDao planDao;

    public PlanServiceImp(PlanDao planDao) {
        this.planDao = planDao;
    }

    @Override
    public void save(Plan plan) {
        planDao.save(plan);
    }

    @Override
    public void update(Plan plan) {
        planDao.update(plan);
    }

    @Override
    public void deleteById(long id) {
        planDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Plan> findById(long id) {
        return Optional.ofNullable(planDao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Plan> findByUserUsername(String username) {
        return Optional.ofNullable(planDao.findByUserUsername(username));
    }

    @Override
    public int existsByIdAndUserUsername(long id, String username) {
        return planDao.existsByIdAndUserUsername(id, username);
    }
}
