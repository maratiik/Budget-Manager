package com.maratik.budgetmanager.controllers;

import com.maratik.budgetmanager.constants.Urls;
import com.maratik.budgetmanager.entities.Goal;
import com.maratik.budgetmanager.entities.User;
import com.maratik.budgetmanager.services.interfaces.GoalService;
import com.maratik.budgetmanager.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Urls.GOAL)
public class GoalController {
    private final GoalService goalService;
    private final UserService userService;

    public GoalController(GoalService goalService, UserService userService) {
        this.goalService = goalService;
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Goal> getGoal(@PathVariable Long id, Principal principal) {
    }

    @GetMapping
    public ResponseEntity<List<Goal>> getAllGoals(Principal principal) {
        List<Goal> goals = goalService.findAllByUserUsername(principal.getName());
        return ResponseEntity.ok(goals);
    }

    @PostMapping
    public ResponseEntity<Void> createGoal(@RequestBody Goal goal, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        goal.setUser(user.get());
        goalService.save(goal);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateGoal(@RequestBody Goal goal, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        goal.setUser(user.get());
        goalService.update(goal);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id, Principal principal) {
    }

}
