package com.maps.search.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by daou on 2018-11-07.
 */
@Component
public class EmbeddedRedis {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() {
        redisServer = new RedisServer(redisPort);
        if(!redisServer.isActive()) redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }









    /*private RedisSentinel redisSentinel;

    private RedisServer redisServerMaster;
    private RedisServer redisServerSlave1;
    private RedisServer redisServerSlave2;
    private RedisServer redisServerSlave3;

    @PostConstruct
    public void startRedis() throws IOException {

        redisServerMaster = new RedisServer(6379);
        redisServerMaster.start();

        redisServerSlave1 = new RedisServer(5555);
        redisServerSlave1.start();

        redisServerSlave2 = new RedisServer(6666);
        redisServerSlave2.start();

        redisServerSlave3 = new RedisServer(7777);
        redisServerSlave3.start();

        redisSentinel = RedisSentinel.builder().masterName("mymaster").masterPort(6379).build();

        redisSentinel.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisSentinel.stop();
        redisServerMaster.stop();
        redisServerSlave1.stop();
        redisServerSlave2.stop();
        redisServerSlave3.stop();
    }
}*/

}
