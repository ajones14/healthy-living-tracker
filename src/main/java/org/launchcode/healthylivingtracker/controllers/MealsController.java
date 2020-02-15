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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static org.launchcode.healthylivingtracker.models.MealType.*;

@Controller
@RequestMapping("meals")
public class MealsController {

    private LocalDate currentDate = LocalDate.now();

    private Collection<Meal> findAllByDate(LocalDate date, Iterable<Meal> collection) {
        Collection<Meal> result = new ArrayList<>();
        for (Meal meal : collection) {
            if (meal.getDate().equals(currentDate)) {
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
        //get logged in user id
        User currentUser = (User) authenticationController.getUserFromSession(session);
        int currentUserId = currentUser.getId();

        //get all meals belonging to logged in user
        Iterable<Meal> currentUserMeals = mealRepository.findAllByUserId(currentUserId);
        //get meals belonging to currentDate
        Collection<Meal> todaysMeals = findAllByDate(currentDate, currentUserMeals);

//        repeat lines 64 - 68 for lunch, dinner, and snacks
        Meal breakfast = null;
        Meal lunch = null;
        Meal dinner = null;
        Meal snack = null;

//        MealType[] enums = {BREAKFAST, LUNCH, DINNER, SNACK};
//        ArrayList<Meal> meals = new ArrayList<>();
//        meals.add(breakfast);
//        meals.add(lunch);
//        meals.add(dinner);
//        meals.add(snack);

//        for (int i = 0; i < enums.length; i++) {
//            if (Objects.isNull(findByType(enums[i], todaysMeals))) {
//                meals.set(i, new Meal(currentUserId, currentDate, enums[i]));
//                mealRepository.save(meals.get(i));
//            } else {
//                meals.set(i, findByType(enums[i], todaysMeals));
//            }
//        }

        //for each MealType, select the current Meal, if none found create and save new Meal
        if (Objects.isNull(findByType(BREAKFAST, todaysMeals))) {
            breakfast = new Meal(currentUserId, currentDate, BREAKFAST);
            mealRepository.save(breakfast);
        } else {
            breakfast = findByType(BREAKFAST, todaysMeals);
        }

        if (Objects.isNull(findByType(LUNCH, todaysMeals))) {
            lunch = new Meal(currentUserId, currentDate, LUNCH);
            mealRepository.save(lunch);
        } else {
            lunch = findByType(LUNCH, todaysMeals);
        }

        if (Objects.isNull(findByType(DINNER, todaysMeals))) {
            dinner = new Meal(currentUserId, currentDate, DINNER);
            mealRepository.save(dinner);
        } else {
            dinner = findByType(DINNER, todaysMeals);
        }

        if (Objects.isNull(findByType(SNACK, todaysMeals))) {
            snack = new Meal(currentUserId, currentDate, SNACK);
            mealRepository.save(snack);
        } else {
            snack = findByType(SNACK, todaysMeals);
        }

        model.addAttribute("test2", breakfast.getDate().toString() + lunch.getDate().toString() +
                dinner.getDate().toString() + snack.getDate().toString());
        model.addAttribute("test", currentUser.getFirstName());
        return "main/meals";
    }

}
