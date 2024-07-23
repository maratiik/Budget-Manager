package com.maratik.budgetmanager.services.implementations;

import com.maratik.budgetmanager.dao.interfaces.GoalDao;
import com.maratik.budgetmanager.entities.Goal;
import com.maratik.budgetmanager.services.interfaces.GoalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GoalServiceImp implements GoalService {
    private final GoalDao goalDao;

    public GoalServiceImp(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    @Override
    public void save(Goal goal) {
        goalDao.save(goal);
    }

    @Override
    public void update(Goal goal) {
        goalDao.update(goal);
    }

    @Override
    public void deleteById(long id) {
        goalDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Goal> findById(long id) {
        return Optional.ofNullable(goalDao.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Goal> findAllByUserUsername(String username) {
        return goalDao.findAllByUserUsername(username);
    }
}
