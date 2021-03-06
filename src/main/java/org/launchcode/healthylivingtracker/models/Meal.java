package org.launchcode.healthylivingtracker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Meal {

    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private LocalDate date;

    @OneToMany(mappedBy = "meal")
    private final List<Food> foods = new ArrayList<>();

    private MealType type;

    public Meal (int userId, LocalDate date, MealType type) {
        this.userId = userId;
        this.date = date;
        this.type = type;
    }

    public Meal () {}

    public MealType getType() {
        return type;
    }

    public void setType(MealType type) {
        this.type = type;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

}
