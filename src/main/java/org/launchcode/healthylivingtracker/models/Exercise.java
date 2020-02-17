package org.launchcode.healthylivingtracker.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int caloriesBurned;

    @NotNull
    private int userId;

    private String details;

    public Exercise(String name, int caloriesBurned, int userId, String details) {
        this.name = name;
        this.caloriesBurned = caloriesBurned;
        this.userId = userId;
        this.details = details;
    }

    public Exercise(String name, int caloriesBurned, int userId) {
        this.name = name;
        this.caloriesBurned = caloriesBurned;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
