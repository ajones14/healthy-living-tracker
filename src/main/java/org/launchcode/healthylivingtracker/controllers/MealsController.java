package org.launchcode.healthylivingtracker.controllers;

import org.launchcode.healthylivingtracker.data.FoodRepository;
import org.launchcode.healthylivingtracker.data.MealRepository;
import org.launchcode.healthylivingtracker.models.Meal;
import org.launchcode.healthylivingtracker.models.MealType;
import org.launchcode.healthylivingtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static org.launchcode.healthylivingtracker.models.MealType.BREAKFAST;

@Controller
@RequestMapping("meals")
public class MealsController {

    private long millis=System.currentTimeMillis();
    private Date currentDate = new java.sql.Date(millis);

    private Collection<Meal> findAllByDate(Date date, Iterable<Meal> collection) {
        Collection<Meal> result = new ArrayList<>();
        for (Meal meal : collection) {
            if (meal.getDate().equals(date)) {
                result.add(meal);
            }
        }
        return result;
    }

    private Meal findByType(MealType mealType, Collection<Meal> collection) {
        for (Meal meal : collection) {
            if (meal.getType().equals(mealType)) {
                return meal;
            }
        }
        return null;
    }

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping
    public String meals(HttpSession session, Model model) {
        User currentUser = (User) authenticationController.getUserFromSession(session);
        int currentUserId = currentUser.getId();

        Iterable<Meal> currentUserMeals = mealRepository.findAllByUserId(currentUserId);
        Collection<Meal> todaysMeals = findAllByDate(currentDate, currentUserMeals);

//        repeat lines 64 - 68 for lunch, dinner, and snacks
        Meal breakfast = null;

        if (Objects.isNull(findByType(BREAKFAST, todaysMeals))) {
            breakfast = new Meal(currentUserId, currentDate, BREAKFAST);
            mealRepository.save(breakfast);
        }
        model.addAttribute("test2", breakfast.getDate().toString());
        model.addAttribute("test", currentUser.getFirstName());
        return "main/meals";
    }

}
