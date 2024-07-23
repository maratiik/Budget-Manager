package com.maratik.budgetmanager.services.implementations;

import com.maratik.budgetmanager.dao.interfaces.*;
import com.maratik.budgetmanager.entities.Expense;
import com.maratik.budgetmanager.entities.Goal;
import com.maratik.budgetmanager.entities.Income;
import com.maratik.budgetmanager.entities.Plan;
import com.maratik.budgetmanager.services.interfaces.ExpenseService;
import com.maratik.budgetmanager.services.interfaces.FeatureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class FeatureServiceImp implements FeatureService {
    private final PlanDao planDao;
    private final ExpenseDao expenseDao;
    private final GoalDao goalDao;
    private final UserDao userDao;
    private final IncomeDao incomeDao;

    public FeatureServiceImp(PlanDao planDao,
                             ExpenseDao expenseDao,
                             GoalDao goalDao,
                             UserDao userDao,
                             IncomeDao incomeDao) {
        this.planDao = planDao;
        this.expenseDao = expenseDao;
        this.goalDao = goalDao;
        this.userDao = userDao;
        this.incomeDao = incomeDao;
    }

    @Override
    public void saveRecommendedPlanByUsername(String username) {
        long income = incomeDao.findAllByUserUsername(username).stream().mapToLong(Income::getValue).sum();
        long needs = (long) (0.5 * income);
        long wants = (long) (0.3 * income);
        long save = income - needs - wants;
        planDao.save(new Plan(
                needs,
                wants,
                save,
                userDao.findByUsername(username)
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Plan> predictPlanByUsername(String username) {
        List<Expense> expenses = expenseDao.findAllByUserUsername(username);
        if (expenses.isEmpty()) {
            return Optional.empty();
        }

        List<Income> incomes = incomeDao.findAllByUserUsername(username);
        if (incomes.isEmpty()) {
            return Optional.empty();
        }

        long income = incomes.stream().mapToLong(Income::getValue).sum();
        long needs = 0;
        long wants = 0;
        long save = 0;

        for (Expense expense : expenses) {
            switch (expense.getType()) {
                case ExpenseService.NEEDS -> needs += expense.getValue();
                case ExpenseService.WANTS -> wants += expense.getValue();
                case ExpenseService.SAVE -> save += expense.getValue();
            }
        }

        double coefficient = (double) ((needs + wants + save) / income);

        return Optional.of(new Plan(
                (long) (needs / coefficient),
                (long) (wants / coefficient),
                (income - (long)(needs / coefficient) - (long)(wants / coefficient)),
                userDao.findByUsername(username)
        ));
    }

    @Override
    public Optional<Plan> savePredictedPlanByUsername(String username) {
        Optional<Plan> plan = predictPlanByUsername(username);
        plan.ifPresent(planDao::save);
        return plan;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Date> getGoalAchievedDateById(long id) {
        Goal goal = goalDao.findById(id);
        if (goal == null) {
            return Optional.empty();
        }

        Plan plan = planDao.findByUserUsername(goal.getUser().getUsername());
        if (plan == null) {
            return Optional.empty();
        }

        int months = (int) (goal.getPrice() / plan.getSave());

        Calendar goalDate = Calendar.getInstance();
        goalDate.add(Calendar.MONTH, months);

        return Optional.of(new Date(goalDate.getTimeInMillis()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Date> getAllGoalsAchievedDateByUserUsername(String username) {
        List<Goal> goals = goalDao.findAllByUserUsername(username);
        if (goals.isEmpty()) {
            return Optional.empty();
        }

        Plan plan = planDao.findByUserUsername(username);
        if (plan == null) {
            return Optional.empty();
        }

        long goalsPrice = goals.stream().mapToLong(Goal::getPrice).sum();
        int months = (int) (goalsPrice / plan.getSave());

        Calendar goalDate = Calendar.getInstance();
        goalDate.add(Calendar.MONTH, months);

        return Optional.of(new Date(goalDate.getTimeInMillis()));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateExpense(Expense expense) {
        List<Expense> expenses = expenseDao.findAllByUserUsernameAndMonthAndYear(
                expense.getUser().getUsername(),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.YEAR)
        );
        if (expenses.isEmpty()) {
            return true;
        }

        Plan plan = planDao.findByUserUsername(expense.getUser().getUsername());
        if (plan == null) {
            return true;
        }

        long spend;
        if (expenses.isEmpty()) {
            spend = 0;
        } else {
            spend = expenses.stream().
                    filter(e -> Objects.equals(e.getType(), expense.getType()))
                    .mapToLong(Expense::getValue)
                    .sum();
        }

        switch (expense.getType()) {
            case ExpenseService.NEEDS -> {
                return (expense.getValue() + spend) <= plan.getNeeds();
            }
            case ExpenseService.WANTS -> {
                return (expense.getValue() + spend) <= plan.getWants();
            }
            case ExpenseService.SAVE -> {
                return (expense.getValue() + spend) <= plan.getSave();
            }
            default -> {
                return true;
            }
        }
    }
}
