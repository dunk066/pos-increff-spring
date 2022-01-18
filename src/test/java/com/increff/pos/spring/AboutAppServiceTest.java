package com.increff.pos.spring;

import com.increff.pos.AboutAppService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AboutAppServiceTest {

	@Autowired
	private AboutAppService service;

	@Test
	public void testServiceApis() {
		assertEquals("pos Application", service.getName());
		assertEquals("1.0", service.getVersion());
	}

}
