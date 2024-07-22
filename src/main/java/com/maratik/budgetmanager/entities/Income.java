package com.maratik.budgetmanager.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value", nullable = false)
    private long value;

    @Column(name = "currency", nullable = false, length = 20)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Income() {}

    public Income(Long value, String currency, User user) {
        this.value = value;
        this.currency = currency;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
        Income income = (Income) o;
        return Objects.equals(id, income.id) && Objects.equals(value, income.value) && Objects.equals(currency, income.currency) && Objects.equals(user, income.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, currency, user);
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                ", user=" + user +
                '}';
    }
}
