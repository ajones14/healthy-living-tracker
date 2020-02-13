package org.launchcode.healthylivingtracker.data;

import org.launchcode.healthylivingtracker.models.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {

}
