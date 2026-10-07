package com.shenzhenxinxizhiye.web_ai_project02;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    private static final Logger log = LoggerFactory.getLogger(LogTest.class);


    @Test
    public void testLog() {


        log.debug("begin o calculate……");

        int sum = 0;
        int[] nums = { 1, 2, 3, 5, 6, 7, 8 };
        for(int num : nums){
            sum += num;
        }
        log.info("The result computation is " + sum);

        log.debug("The calculation is compleeted!");
    }
}
