package com.maratik.budgetmanager.services.interfaces;

import com.maratik.budgetmanager.entities.Goal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GoalService {
    void save(Goal goal);
    void update(Goal goal);
    void deleteById(long id);
    Optional<Goal> findById(long id);
    List<Goal> findAllByUserUsername(String username);
    int existsByIdAndUserUsername(long id, String username);
}
