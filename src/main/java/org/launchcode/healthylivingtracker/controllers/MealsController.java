package org.launchcode.healthylivingtracker.controllers;

import org.launchcode.healthylivingtracker.data.FoodRepository;
import org.launchcode.healthylivingtracker.data.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("meals")
public class MealsController {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MealRepository mealRepository;

    @GetMapping
    public String meals() {
        return "main/meals";
    }

}
