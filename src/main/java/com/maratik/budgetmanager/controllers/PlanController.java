package com.maratik.budgetmanager.controllers;

import com.maratik.budgetmanager.constants.Urls;
import com.maratik.budgetmanager.entities.Plan;
import com.maratik.budgetmanager.entities.User;
import com.maratik.budgetmanager.services.interfaces.PlanService;
import com.maratik.budgetmanager.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Urls.PLAN)
public class PlanController {
    private final PlanService planService;
    private final UserService userService;

    public PlanController(PlanService planService, UserService userService) {
        this.planService = planService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Plan> getPlan(Principal principal) {
        Optional<Plan> plan = planService.findByUserUsername(principal.getName());
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPlan(@RequestBody Plan plan, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        plan.setUser(user.get());
        planService.save(plan);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePlan(@RequestBody Plan plan, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        plan.setUser(user.get());
        planService.update(plan);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlan(Principal principal) {
        Optional<Plan> plan = planService.findByUserUsername(principal.getName());
        if (plan.isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else {
            planService.deleteById(plan.get().getId());
            return ResponseEntity.ok().build();
        }
    }


}
