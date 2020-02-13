package org.launchcode.healthylivingtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("exercise")
public class ExerciseController {

    @GetMapping
    public String exercise() {
        return "main/exercise";
    }

}
