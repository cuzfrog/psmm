package com.github.cuzfrog.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.cuzfrog.psmm.PsmmConfiguration;

/**
 * FactoryPool that uses a {@code ConcurrentHashMap} for caching PsmmFactory.
 * 
 * <p>{@code Map<Long, PsmmFactory>} {@link #pool} is the pool member. Notice that key type is Long, which is
 * actually every thread's Id. Because JVM assures that no duplicate thread IDs at one time, after each thread grabs
 * its factory's reference through this {@code ConcurrentHashMap}, they're free of worrying about concurrency. Plus
 * there's no key conflicting, thus seeking and fetching factory concurrently only affects very little performance.
 * 
 * @author Cause Chung
 *
 */
final class MapFactoryPool extends AbstractFactoryPool {

	private final Map<Long, PsmmFactory> pool;
	

	MapFactoryPool(PsmmConfiguration config) {
		super(config);
		pool = new ConcurrentHashMap<>(config.getFactoryPoolSize());

		// modules.putAll(config.getCustomModules());
				// because I want factory invisible from outside,it doesn't support
				// custom module for now.
	}

	// Functionalities:
	@Override
	protected PsmmFactory createOrFetch() {
		@SuppressWarnings("boxing") 
		Long threadId = Thread.currentThread().getId();
		PsmmFactory psmmFactory;
		
		if (pool.containsKey(threadId)) {
			psmmFactory = pool.get(threadId);
		} else {
			psmmFactory = new PsmmFactoryImpl();
			pool.put(threadId, psmmFactory);
		}
		
		return psmmFactory;
	}
}
