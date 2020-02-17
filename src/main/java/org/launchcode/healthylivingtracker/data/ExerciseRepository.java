package org.launchcode.healthylivingtracker.data;

import org.launchcode.healthylivingtracker.models.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

    Iterable<Exercise> findAllByUserId(int userId);

}
