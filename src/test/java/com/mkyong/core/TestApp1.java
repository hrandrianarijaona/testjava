package com.mkyong.core;

import org.junit.Assert;
import org.junit.Test;

public class TestApp1 {

	@Test
	public void testPrintHelloWorld() {

		Assert.assertEquals(App.getHelloWorld(),  "Hello World");
		

	}

}
