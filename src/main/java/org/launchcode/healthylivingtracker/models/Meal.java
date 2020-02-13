package org.launchcode.healthylivingtracker.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Meal {

    private int userId;

    private Date date;

    @OneToMany(mappedBy = "food")
    private final List<Food> foods = new ArrayList<>();

    private MealType type;

    public Meal (int userId, Date date, MealType type) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
