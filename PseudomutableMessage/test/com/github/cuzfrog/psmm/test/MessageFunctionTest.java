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
import com.github.cuzfrog.psmm.exceptions.PsmmException;
import com.github.cuzfrog.psmm.exceptions.PsmmRuntimeException;

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
		UMessage message = Messages.create().set("test1", random.nextInt(10000)).cook();
		for (int i = 0; i < 200; i++) {
			message = message.set("test1", random.nextInt(10000)).cook();
		}
		System.out.println(message.depth());

		try {
			for (int i = 0; i < 200; i++) {
				message = message.set("test1", random.nextInt(10000)).cook();
			}
		} catch (PsmmRuntimeException e) {
			// OK
			System.out.println(message.depth());
		}

	}

	@SuppressWarnings("boxing")
	@Test
	public void testRegression() {
		Random random = new Random();
		UMessage message = Messages.create().set("test1", random.nextInt(10000)).cook();
		for (int i = 0; i < 200; i++) {
			message = message.set("test1", random.nextInt(10000)).cook();
		}
		System.out.println(message.depth());

		try {
			for (int i = 0; i < 200; i++) {
				message = message.regress();
			}
		} catch (PsmmException e) {
			fail("regress shoud be in bound");
		}
		System.out.println(message.depth());
		try {
			for (int i = 0; i < 200; i++) {
				message = message.regress();
			}
		} catch (PsmmException e) {
			// OK
			System.out.println(message.depth());
		}
	}

}
