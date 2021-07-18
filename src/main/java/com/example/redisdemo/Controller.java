package com.example.redisdemo;

import com.example.redisdemo.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    RedisService redisService;

    @GetMapping("/testRedis")
    public TestDO testRedis(){
        TestDO testDO = new TestDO();
        String key = "test";
        Object userCache = redisService.get(key);
        if (userCache == null) {
            testDO.setTestName("testRedis");
            redisService.set(key, testDO);
        } else {
            testDO = (TestDO)userCache;
        }
        return testDO;
    }


    @PostMapping("/deleteRedis")
    public void testDeleteRedis(String key){
//        String key = "test";
        redisService.delete(key);
    }
}
