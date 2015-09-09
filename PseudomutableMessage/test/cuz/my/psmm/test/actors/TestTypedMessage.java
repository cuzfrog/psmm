package cuz.my.psmm.test.actors;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cuz.my.psmm.Messages.Style;

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
	public void testTypedCachedLinkedMap() {
		threadFailKey.set(false);
		typedTest(Style.RETAINED_LINKED_MAP,Integer.class);
		assertFalse(threadFailKey.get());
	}

	@Test
	public void testTypedCachedFlatMap() {
		threadFailKey.set(false);
		typedTest(Style.RETAINED_FLAT_MAP,Integer.class);
		assertFalse(threadFailKey.get());
	}
}
