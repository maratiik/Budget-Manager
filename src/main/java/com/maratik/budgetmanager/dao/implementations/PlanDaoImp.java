package com.maratik.budgetmanager.dao.implementations;

import com.maratik.budgetmanager.dao.interfaces.PlanDao;
import com.maratik.budgetmanager.entities.Plan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanDaoImp implements PlanDao {

    @PersistenceContext
    private EntityManager entityManager;

    public PlanDaoImp() {}

    @Override
    public void save(Plan plan) {
        entityManager.persist(plan);
    }

    @Override
    public void update(Plan plan) {
        entityManager.merge(plan);
    }

    @Override
    public void deleteById(long id) {
        entityManager.createQuery("DELETE FROM Plan WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Plan findById(long id) {
        return entityManager.find(Plan.class, id);
    }

    @Override
    public List<Plan> findAllByUserUsername(String username) {
        return entityManager.createQuery("from Plan where user.username = :username", Plan.class)
                .setParameter("username", username)
                .getResultList();
    }
}
