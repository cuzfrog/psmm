package com.github.cuzfrog.psmm.test.actors;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.cuzfrog.psmm.Messages.Style;

public class TestTypedMessage extends TestAbstractActorSimulation {
	@Before
	public void setUp() throws Exception {
		
		this.senderModule = new SenderModuleTypedMessage();
		super.setUp();

	}

	@After
	public void tearDown() {
		super.tearDown();

	}


	@Test
	public void testTypedLinkedMap() {
		threadFailKey.set(false);
		typedTest(Style.LINKED_MAP, Integer.class);
		assertFalse(threadFailKey.get());
	}

	@Test
	public void testTypedFlatMap() {
		threadFailKey.set(false);
		typedTest(Style.FLAT_MAP,Integer.class);
		assertFalse(threadFailKey.get());
	}
	
	@Test
	public void testValueTypedLinkedMap() {
		threadFailKey.set(false);
		typedTest(Style.VALUE_LINKED_MAP, Integer.class);
		assertFalse(threadFailKey.get());
	}
	
	@Test
	public void testValueTypedFlatMap() {
		threadFailKey.set(false);
		typedTest(Style.VALUE_FLAT_MAP,Integer.class);
		assertFalse(threadFailKey.get());
	}

}
