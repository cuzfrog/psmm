package cuz.psmm.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cuz.psmm.accessories.Configuration;
import cuz.psmm.factory.modules.Module;
import cuz.psmm.message.Message;

/**
 * 
 * @author cuzfrog
 *
 */
class ThreadFactoryPool implements FactoryPool  {

	private Map<Long, PsmmFactory> pool;
	private Map<Message.Type, Module> modules;

	ThreadFactoryPool(Configuration config) {
		pool = new ConcurrentHashMap<>();
		modules = Module.createModuleMap();

	}

	// Functionalities:
	/* (non-Javadoc)
	 * @see cuz.psmm.FactoryPool#seekFactory(cuz.psmm.Messages.Type)
	 */
	@Override
	public PsmmFactory seekFactory(Message.Type type) {
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
