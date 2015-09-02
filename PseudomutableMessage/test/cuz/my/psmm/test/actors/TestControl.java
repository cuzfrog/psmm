package cuz.my.psmm.test.actors;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestControl extends TestAbstractActorSimulation {

	@Before
	public void setUp() throws Exception {
		this.senderModule = new SenderModuleControl();
		super.setUp();

	}

	@After
	public void tearDown() {
		super.tearDown();
	}



	@Test
	public void testControlParcel() {
		threadFailKey.set(false);
		controlTest();
		assertFalse(threadFailKey.get());
	}

	@Test
	public void testControlDoNothing() {
		threadFailKey.set(false);
		// do nothing
		assertFalse(threadFailKey.get());
	}

}
