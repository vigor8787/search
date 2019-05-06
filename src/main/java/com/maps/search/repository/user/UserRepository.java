package com.maps.search.repository.user;

import com.maps.search.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findBy_id(String Id);
}
