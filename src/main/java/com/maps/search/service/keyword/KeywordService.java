package com.maps.search.service.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;

@Service
public class KeywordService {

    @Autowired
    private ReactiveRedisOperations<String,String> reactiveRedisOperations;

    @Autowired
    private RedisOperations<String,String> redisOperations;

    public Flux<ZSetOperations.TypedTuple<String>> getLankKeywords() {
        return reactiveRedisOperations
                .opsForZSet()
                .reverseRangeWithScores("keyword", Range.of(Range.Bound.inclusive(0l),Range.Bound.inclusive(9l)));
    }

    public Mono<Double> countViews(String keyword) {
        return reactiveRedisOperations
                .opsForZSet()
                .incrementScore("keyword",keyword,1);
    }

    @PostConstruct
    private void dummyData() {
        redisOperations.opsForZSet().incrementScore("keyword","kakao",1);
        redisOperations.opsForZSet().incrementScore("keyword","coffee",5);
        redisOperations.opsForZSet().incrementScore("keyword","starbucks",1);
        redisOperations.opsForZSet().incrementScore("keyword","samsung",12);
        redisOperations.opsForZSet().incrementScore("keyword","tomntoms",1);
        redisOperations.opsForZSet().incrementScore("keyword","engelinus",1);
        redisOperations.opsForZSet().incrementScore("keyword","cloud",1);
        redisOperations.opsForZSet().incrementScore("keyword","spring",1);
        redisOperations.opsForZSet().incrementScore("keyword","mlb",1);
        redisOperations.opsForZSet().incrementScore("keyword","mac",1);
        redisOperations.opsForZSet().incrementScore("keyword","window",1);
        redisOperations.opsForZSet().incrementScore("keyword","chicken",11030);
        redisOperations.opsForZSet().incrementScore("keyword","pizza",5);
        redisOperations.opsForZSet().incrementScore("keyword","korea",1);
        redisOperations.opsForZSet().incrementScore("keyword","seoul",20);
    }
}
