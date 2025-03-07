package com.KK.MyJournalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Disabled
    @Test
    void testSendMail()
    {
        redisTemplate.opsForValue().set("email","gakpocool@gmail.com");
        Object email=redisTemplate.opsForValue().get("email");
        Object salary=redisTemplate.opsForValue().get("salary");
        log.info("Retrieved email: {}", email);
    }
}
