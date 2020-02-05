package org.launchcode.healthylivingtracker.data;

import org.launchcode.healthylivingtracker.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
