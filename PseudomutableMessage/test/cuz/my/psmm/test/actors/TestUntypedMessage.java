package cuz.my.psmm.test.actors;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cuz.my.psmm.Messages.Style;

public class TestUntypedMessage extends TestAbstractActorSimulation {

	@Before
	public void setUp() throws Exception {
		this.senderModule = new SenderModuleMessage();
		super.setUp();

	}

	@After
	public void tearDown() {
		super.tearDown();

	}


	@Test
	public void testUntypedLinkedMap() {
		threadFailKey.set(false);
		untypedTest(Style.LINKED_MAP);
		assertFalse(threadFailKey.get());
	}

	@Test
	public void testUntypedFlatMap() {
		threadFailKey.set(false);
		untypedTest(Style.FLAT_MAP);
		assertFalse(threadFailKey.get());
	}

	@Test
	public void testUntypedCachedLinkedMap() {
		threadFailKey.set(false);
		untypedTest(Style.RETAINED_LINKED_MAP);
		assertFalse(threadFailKey.get());
	}

	@Test
	public void testUntypedCachedFlatMap() {
		threadFailKey.set(false);
		untypedTest(Style.RETAINED_FLAT_MAP);
		assertFalse(threadFailKey.get());
	}
	
	
}
