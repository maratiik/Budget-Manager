package com.maratik.budgetmanager.controllers;

import com.maratik.budgetmanager.constants.Urls;
import com.maratik.budgetmanager.entities.Expense;
import com.maratik.budgetmanager.entities.User;
import com.maratik.budgetmanager.entities.data_classes.MonthYearDate;
import com.maratik.budgetmanager.services.implementations.IncomeServiceImp;
import com.maratik.budgetmanager.services.interfaces.ExpenseService;
import com.maratik.budgetmanager.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(Urls.EXPENSE)
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Expense> getExpense(@PathVariable Long id, Principal principal) {
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(Principal principal) {
        List<Expense> expenses = expenseService.findAllByUserUsername(principal.getName());
        return ResponseEntity.ok(expenses);
    }

    @PostMapping
    public ResponseEntity<Void> createExpense(@RequestBody Expense expense, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        expense.setUser(user.get());
        expenseService.save(expense);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateExpense(@RequestBody Expense expense, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        expense.setUser(user.get());
        expenseService.update(expense);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id, Principal principal) {
    }

    @GetMapping(Urls.EXPENSE_SUMMARY)
    public ResponseEntity<Map<String, Long>> getExpenseSumByDate(@RequestParam MonthYearDate date, Principal principal) {
        return ResponseEntity.ok(
                expenseService.findExpenseSumsByUserUsernameAndMonthAndYear(
                        principal.getName(),
                        date.month(),
                        date.year()
                )
        );
    }
}
