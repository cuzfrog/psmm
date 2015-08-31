package cuz.my.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FactoryPool that uses a {@code ConcurrentHashMap} for caching PsmmFactory.
 * 
 * <p>{@code Map<Long, PsmmFactory>} {@link #pool} is the pool member. Notice that key type is Long, which is
 * actually every thread's Id. Because JVM assures that no duplicate thread IDs at one time, after each thread grabs
 * its factory's reference through this {@code ConcurrentHashMap}, they're free of worrying about concurrency. Plus
 * there's no key conflicting, thus seeking and fetching factory concurrently only affects very little performance.
 * 
 * @author cuzfrog
 *
 */
class MapFactoryPool implements FactoryPool {

	private final Map<Long, PsmmFactory> pool;
	private final Map<String, Module> modules;

	MapFactoryPool(int initialSize) {
		pool = new ConcurrentHashMap<>(initialSize);
		modules = Module.createModuleMap();

		// modules.putAll(config.getCustomModules());
		// because I want factory invisible from outside,it doesn't support
		// custom module for now.
	}

	// Functionalities:
	/*
	 * (non-Javadoc)
	 * 
	 * @see cuz.psmm.FactoryPool#seekFactory(cuz.psmm.Messages.Type)
	 */
	@Override
	public PsmmFactory seekFactory(Messages.Style type) {
		// TODO Auto-generated method stub
		Long threadId = Thread.currentThread().getId();
		PsmmFactory psmmFactory;
		String name = type.toString();
		if (pool.containsKey(threadId)) {
			psmmFactory = pool.get(threadId);
		} else {
			psmmFactory = new PsmmFactoryImpl();
			pool.put(threadId, psmmFactory);
		}
		psmmFactory.assemble(modules.get(name), type);
		// DEBUG:
		// System.out.println(modules.get(type));
		return psmmFactory;
	}
}
