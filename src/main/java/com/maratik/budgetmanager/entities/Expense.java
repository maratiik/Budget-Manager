package com.maratik.budgetmanager.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "value", nullable = false)
    private long value;

    @Column(name = "type", nullable = false)
    private String type;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Expense() {}

    public Expense(Long value, String type, Date date, User user) {
        this.value = value;
        this.type = type;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) && Objects.equals(value, expense.value) && Objects.equals(type, expense.type) && Objects.equals(date, expense.date) && Objects.equals(user, expense.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, type, date, user);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
