package com.maps.search.keyword;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class KeywordTest {

    @Autowired
    private RedisOperations<String,String> redisOperations;

    @Before
    public void setUp() {
        dummyData();
    }

    @Test
    public void countAndLankInRedisTest() {
        Set<ZSetOperations.TypedTuple<String>> setRank = redisOperations
                .opsForZSet()
                .reverseRangeWithScores("keyword", 0, 9);

         assertThat(setRank.size()).isEqualTo(10);
         assertThat(setRank.iterator().next().getValue()).isEqualTo("chicken");
    }

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
