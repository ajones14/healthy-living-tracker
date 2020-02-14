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

import static org.launchcode.healthylivingtracker.models.MealType.BREAKFAST;

@Controller
@RequestMapping("meals")
public class MealsController {


    private LocalDate currentDate = LocalDate.now();


    private Collection<Meal> findAllByDate(LocalDate date, Iterable<Meal> collection) {
        Collection<Meal> result = new ArrayList<>();
        for (Meal meal : collection) {
            System.out.println("inside loop");
            System.out.println(date.toString());
            System.out.println(currentDate.toString());
            if (meal.getDate().equals(currentDate)) {
                System.out.println("add meal");
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

        System.out.println(currentDate);

        Iterable<Meal> currentUserMeals = mealRepository.findAllByUserId(currentUserId);

        Collection<Meal> todaysMeals = findAllByDate(currentDate, currentUserMeals);
        System.out.println(todaysMeals.toString());

//        repeat lines 64 - 68 for lunch, dinner, and snacks
        Meal breakfast = null;

        if (Objects.isNull(findByType(BREAKFAST, todaysMeals))) {
            breakfast = new Meal(currentUserId, currentDate, BREAKFAST);
            mealRepository.save(breakfast);
        } else {
            breakfast = findByType(BREAKFAST, todaysMeals);
        }

        model.addAttribute("test2", breakfast.getDate().toString());
        model.addAttribute("test", currentUser.getFirstName());
        return "main/meals";
    }

}
