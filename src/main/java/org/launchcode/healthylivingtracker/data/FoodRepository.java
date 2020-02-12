package org.launchcode.healthylivingtracker.data;

import org.launchcode.healthylivingtracker.models.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<Food, Integer> {

}
