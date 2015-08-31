package cuz.my.psmm;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;

import cuz.my.psmm.Messages.Style;
import cuz.my.test.util.Timer;

public class MapFactoryPoolTest extends MyAbstractTest {
	MapFactoryPool pool;
	Map<String, Module> modules;

	@Before
	public void setUpBefore() {
		initiate(0);
		pool = new MapFactoryPool(16);
		modules = Module.createModuleMap();
	}

	@Test
	public void behaviorTestSeekFactory() {
		Set<PsmmFactory> factorys = new HashSet<>();
		for (int i = 0; i < 10000; i++) {
			PsmmFactory factory = pool.seekFactory(randomType());
			factorys.add(factory);
		}
		assertEquals(1, factorys.size());  //when single threaded, there should be only one factory instance.

		factorys.clear();
		int threadCnt=6; //how many threads
		Timer timer=new Timer("multithread seekFactory");
		List<PsmmFactory> results = call(() -> {
			return pool.seekFactory(randomType());
		}, 1000, threadCnt);
		factorys.addAll(results);
		timer.stop();
		assertEquals(threadCnt, factorys.size());  //when multithreaded, there should be as many factory instances as the threads.
		
	}


	@Test
	public void loadTestSeekFactory() {
		run(() -> {
			pool.seekFactory(randomType());
		});
	}


	@Test
	public void loadTestCreateFactory() {
		run(() -> {
			PsmmFactory factory = new PsmmFactoryImpl();
			Style type = types[ThreadLocalRandom.current().nextInt(types.length - 1)];
			factory.assemble(modules.get(type.toString()), type);
		});
	}
}
