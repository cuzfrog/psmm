package cuz.my.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A {@code ConcurrentHashMap} is used for cache PsmmFactory.
 * 
 * <p>
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
	public PsmmFactory seekFactory(Messages.Type type) {
		// TODO Auto-generated method stub
		Long threadId = Thread.currentThread().getId();
		PsmmFactory psmmFactory;
		String name = type.toString();
		if (pool.containsKey(threadId)) {
			psmmFactory = pool.get(threadId);
		} else {
			psmmFactory = new GeneralPsmmFactory();
			pool.put(threadId, psmmFactory);
		}
		psmmFactory.assemble(modules.get(name), type);
		// DEBUG:
		// System.out.println(modules.get(type));
		return psmmFactory;
	}
}
