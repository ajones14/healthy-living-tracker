package org.launchcode.healthylivingtracker.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Meal meal;

    @NotNull
    private String name;

    @NotNull
    private int caloricValue;

    public Food () {}

    public Food (String name, int caloricValue, Meal meal) {
        this.name = name;
        this.caloricValue = caloricValue;
        this.meal = meal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaloricValue() {
        return caloricValue;
    }

    public void setCaloricValue(int caloricValue) {
        this.caloricValue = caloricValue;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getId() {
        return id;
    }
}
