package cuz.my.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author cuzfrog
 *
 */
class ThreadFactoryPool implements FactoryPool  {

	private final Map<Long, PsmmFactory> pool;
	private final Map<String, Module> modules;

	ThreadFactoryPool(Configuration config) {
		pool = new ConcurrentHashMap<>();
		modules = Module.createModuleMap();

		modules.putAll(config.getCustomModules());
	}

	// Functionalities:
	/* (non-Javadoc)
	 * @see cuz.psmm.FactoryPool#seekFactory(cuz.psmm.Messages.Type)
	 */
	@Override
	public PsmmFactory seekFactory(Messages.Type type) {
		// TODO Auto-generated method stub
		Long threadId = Thread.currentThread().getId();
		PsmmFactory psmmFactory;
		String name=type.toString();
		if (pool.containsKey(threadId)) {
			psmmFactory = pool.get(threadId);
		} else {
			psmmFactory = new GeneralPsmmFactory();
			pool.put(threadId, psmmFactory);
		}
		psmmFactory.assemble( modules.get(name),type);
		//DEBUG:
		//System.out.println(modules.get(type));
		return psmmFactory;
	}
}
