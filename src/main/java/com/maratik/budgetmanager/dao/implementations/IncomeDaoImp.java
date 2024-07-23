package com.maratik.budgetmanager.dao.implementations;

import com.maratik.budgetmanager.dao.interfaces.IncomeDao;
import com.maratik.budgetmanager.entities.Income;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomeDaoImp implements IncomeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public IncomeDaoImp() {}

    @Override
    public void save(Income income) {
        entityManager.persist(income);
    }

    @Override
    public void update(Income income) {
        entityManager.merge(income);
    }

    @Override
    public void deleteById(long id) {
        entityManager.createQuery("delete from Income where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Income findById(long id) {
        return entityManager.find(Income.class, id);
    }

    @Override
    public List<Income> findAllByUserUsername(String username) {
        return entityManager.createQuery("from Income where user.username = :username", Income.class)
                .setParameter("username", username)
                .getResultList();
    }
}
