package com.poc2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentApplication.class)
class StudentApplicationTest {

	@Test
	void contextLoads() {
	}

	@Test
	public void employeeApplication() {
	    StudentApplication.main(new String[] {});
	}

}
