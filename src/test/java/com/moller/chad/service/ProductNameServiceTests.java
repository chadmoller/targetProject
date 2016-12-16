package com.moller.chad.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductNameServiceTests {

	@Autowired
	ProductNameService productNameService;

	@Test
	public void integrationTest() {
		String name = productNameService.getName(13860427);
		Assert.assertEquals("Conan the Barbarian (dvd_video)", name);
	}

}
