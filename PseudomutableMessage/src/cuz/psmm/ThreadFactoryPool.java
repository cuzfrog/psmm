package cuz.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cuz.psmm.Messages.Type;
import cuz.psmm.accessoaries.Configuration;
import cuz.psmm.factoryModules.Module;
import cuz.psmm.factoryModules.Modules;

/**
 * 
 * @author cuzfrog
 *
 */
class ThreadFactoryPool implements FactoryPool  {

	private Map<Long, PsmmFactory> pool;
	private Map<Messages.Type, Module> modules;

	ThreadFactoryPool(Configuration config) {
		pool = new ConcurrentHashMap<>();
		modules = Modules.createModuleMap();

	}

	// Functionalities:
	/* (non-Javadoc)
	 * @see cuz.psmm.FactoryPool#seekFactory(cuz.psmm.Messages.Type)
	 */
	@Override
	public PsmmFactory seekFactory(Type type) {
		// TODO Auto-generated method stub
		Long threadId = Thread.currentThread().getId();
		PsmmFactory psmmFactory;
		if (pool.containsKey(type)) {
			psmmFactory = pool.get(threadId);
		} else {
			psmmFactory = new GeneralPsmmFactory();
			pool.put(threadId, psmmFactory);
		}
		psmmFactory.assemble( modules.get(type));
		//DEBUG:
		//System.out.println(modules.get(type));
		return psmmFactory;
	}
}
