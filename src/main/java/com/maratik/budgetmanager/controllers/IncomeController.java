package com.maratik.budgetmanager.controllers;

import com.maratik.budgetmanager.constants.Urls;
import com.maratik.budgetmanager.entities.Income;
import com.maratik.budgetmanager.entities.User;
import com.maratik.budgetmanager.services.interfaces.IncomeService;
import com.maratik.budgetmanager.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Urls.INCOME)
public class IncomeController {
    private final IncomeService incomeService;
    private final UserService userService;

    public IncomeController(IncomeService incomeService, UserService userService) {
        this.incomeService = incomeService;
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Income> getIncome(@PathVariable Long id, Principal principal) {
        Optional<Income> income = incomeService.findById(id);
        if (income.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            if (income.get().getUser().getUsername().equals(principal.getName())) {
                return ResponseEntity.ok(income.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<Income>> getAllIncomes(Principal principal) {
        List<Income> incomes = incomeService.findAllByUserUsername(principal.getName());
        return ResponseEntity.ok(incomes);
    }

    @PostMapping
    public ResponseEntity<Void> createIncome(@RequestBody Income income, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        income.setUser(user.get());
        incomeService.save(income);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateIncome(@RequestBody Income income, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        income.setUser(user.get());
        incomeService.update(income);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id, Principal principal) {
        Optional<Income> income = incomeService.findById(id);
        if (income.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            if (income.get().getUser().getUsername().equals(principal.getName())) {
                incomeService.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }
}
