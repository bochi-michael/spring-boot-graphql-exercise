package com.example.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {BlogApplication.class},
//        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PostApplicationTests {

    @Test
    public void contextLoads() {
    }
}
