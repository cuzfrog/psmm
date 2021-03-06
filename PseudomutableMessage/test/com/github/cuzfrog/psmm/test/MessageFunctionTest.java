package com.github.cuzfrog.psmm.test;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.UMessage;

public class MessageFunctionTest {
    Logger logger=LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@BeforeClass
	public static void setupAll() {
		PsmmSystem.initiate();
	}

	@SuppressWarnings("boxing")
	@Test
	public void testMaxDepth() {
		Random random = new Random();
		UMessage message = Messages.builder().set("test1", random.nextInt(10000)).build();
		for (int i = 0; i < 200; i++) {
			message = message.set("test1", random.nextInt(10000)).build();
		}
		System.out.println(message.depth());

		try {
			for (int i = 0; i < 200; i++) {
				message = message.set("test1", random.nextInt(10000)).build();
			}
		} catch (Exception e) {
			// OK
			System.out.println(message.depth());
		}

	}

	@SuppressWarnings("boxing")
	@Test
	public void testRegression() {
		Random random = new Random();
		UMessage message = Messages.builder().set("test1", random.nextInt(10000)).build();
		for (int i = 0; i < 200; i++) {
			message = message.set("test1", random.nextInt(10000)).build();
		}
		System.out.println(message.depth());


			for (int i = 0; i < 200; i++) {
				message = message.regress();
				if(message==null){
					fail("regress shoud be in bound");
				}
			}

		System.out.println(message.depth());
		try {
			for (int i = 0; i < 200; i++) {
				message = message.regress();
			}
		} catch (NullPointerException e) {
			// OK, this is expected.
		}
	}

}
