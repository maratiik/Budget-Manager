package com.maratik.budgetmanager.dao.implementations;

import com.maratik.budgetmanager.dao.interfaces.ExpenseDao;
import com.maratik.budgetmanager.entities.Expense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseDaoImp implements ExpenseDao {

    @PersistenceContext
    private EntityManager entityManager;

    public ExpenseDaoImp() {}

    @Override
    public void save(Expense expense) {
        entityManager.persist(expense);
    }

    @Override
    public void update(Expense expense) {
        entityManager.merge(expense);
    }

    @Override
    public void deleteById(long id) {
        entityManager.createQuery("delete from Expense where id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Expense findById(long id) {
        return entityManager.find(Expense.class, id);
    }

    @Override
    public List<Expense> findAllByUserUsername(String username) {
        return entityManager.createQuery("from Expense where user.username = :username", Expense.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<Expense> findAllByUserUsernameAndMonthAndYear(String username, int month, int year) {
        return entityManager.createQuery(
                "from Expense where user.username = :username and year(date) = :year and month(date) = :month",
                        Expense.class)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList();
    }

    @Override
    public int existsByIdAndUserUsername(long id, String username) {
        Expense expense = findById(id);

        if (expense == null) return -1;

        if (expense.getUser().getUsername().equals(username)) {
            return 1;
        }

        return 0;
    }
}
