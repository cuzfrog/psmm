package cuz.my.psmm.test.actors;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import cuz.my.psmm.Messages.Style;

public class TestTypedMessage extends TestAbstractActorSimulation {
	@Before
	public void setUp() throws Exception {
		//this.senderModule = new SenderModuleMessage();
		super.setUp();

	}

	@After
	public void tearDown() {
		super.tearDown();

	}
	
	@Test
	public void testTypedMessage() throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(this.getClass().getSimpleName())
				.forks(1).build();
		new Runner(opt).run();
	}

	@Benchmark
	public void testTypedLinkedMap() {
		threadFailKey.set(false);
		typedTest(Style.LINKED_MAP);
		assertFalse(threadFailKey.get());
	}

	@Benchmark
	public void testTypedFlatMap() {
		threadFailKey.set(false);
		typedTest(Style.FLAT_MAP);
		assertFalse(threadFailKey.get());
	}

	@Benchmark
	public void testTypedCachedLinkedMap() {
		threadFailKey.set(false);
		typedTest(Style.CACHED_LINKED_MAP);
		assertFalse(threadFailKey.get());
	}

	@Benchmark
	public void testTypedCachedFlatMap() {
		threadFailKey.set(false);
		typedTest(Style.CACHED_FLAT_MAP);
		assertFalse(threadFailKey.get());
	}
}
