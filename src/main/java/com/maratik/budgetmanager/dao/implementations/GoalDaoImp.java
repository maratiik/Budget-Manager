package com.maratik.budgetmanager.dao.implementations;

import com.maratik.budgetmanager.dao.interfaces.GoalDao;
import com.maratik.budgetmanager.entities.Goal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoalDaoImp implements GoalDao {

    @PersistenceContext
    private EntityManager entityManager;

    public GoalDaoImp() {}

    @Override
    public void save(Goal goal) {
        entityManager.persist(goal);
    }

    @Override
    public void update(Goal goal) {
        entityManager.merge(goal);
    }

    @Override
    public void deleteById(long id) {
        entityManager.createQuery("delete from Goal where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Goal findById(long id) {
        return entityManager.find(Goal.class, id);
    }

    @Override
    public List<Goal> findAllByUserUsername(String username) {
        return entityManager.createQuery("from Goal where user.username = :username", Goal.class)
                .setParameter("username", username)
                .getResultList();
    }
}
