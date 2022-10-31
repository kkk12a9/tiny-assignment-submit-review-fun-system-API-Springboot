package com.fun.smallschool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmallSchoolApplicationTests {

	@Test
	void contextLoads() {
        int x = 1;
        int y = 2;
    
        assertEquals(3, x + y);
	}

}
