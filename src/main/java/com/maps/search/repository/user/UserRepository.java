package com.maps.search.repository.user;


import com.maps.search.model.user.User;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface UserRepository extends ReactiveRedisOperations<User, String> {
    Mono<User> findBy_id(String Id);
}
