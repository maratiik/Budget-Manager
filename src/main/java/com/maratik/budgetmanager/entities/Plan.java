package com.maratik.budgetmanager.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "needs")
    private long needs;

    @Column(name = "wants")
    private long wants;

    @Column(name = "save")
    private long save;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Plan() {}

    public Plan(Long needs, Long wants, Long save, User user) {
        this.needs = needs;
        this.wants = wants;
        this.save = save;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNeeds() {
        return needs;
    }

    public void setNeeds(Long needs) {
        this.needs = needs;
    }

    public Long getWants() {
        return wants;
    }

    public void setWants(Long wants) {
        this.wants = wants;
    }

    public Long getSave() {
        return save;
    }

    public void setSave(Long save) {
        this.save = save;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(id, plan.id) && Objects.equals(needs, plan.needs) && Objects.equals(wants, plan.wants) && Objects.equals(save, plan.save) && Objects.equals(user, plan.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, needs, wants, save, user);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", needs=" + needs +
                ", wants=" + wants +
                ", save=" + save +
                ", user=" + user +
                '}';
    }
}
