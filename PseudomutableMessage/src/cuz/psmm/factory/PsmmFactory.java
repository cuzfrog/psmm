package cuz.psmm.factory;

import cuz.psmm.accessories.Configuration;
import cuz.psmm.accessories.NotThreadSafe;
import cuz.psmm.factory.modules.Module;
import cuz.psmm.message.Message;

@NotThreadSafe
public abstract class PsmmFactory{
	
	private static FactoryPool factoryPool = new ThreadFactoryPool(
			new Configuration());
	
	public abstract <T> PsmmFactory assemble(Module module);
	public abstract <T> Message<T> commit(Message<T> messageBeingWrapped);
	public abstract <T> void set(String key,T datum);
	
	public static PsmmFactory seekFactory(Message.Type type){
		return factoryPool.seekFactory(type);
	}
}
