package cuz.my.psmm;

import cuz.my.psmm.accessories.NotThreadSafe;
import cuz.my.psmm.data.Data;

@NotThreadSafe
abstract class PsmmFactory{
	
	private static FactoryPool factoryPool = new ThreadFactoryPool(
			new Configuration());
	
	public abstract <T> PsmmFactory assemble(Module module, Messages.Type type);
	public abstract <T> TypedMessage<T> commit(TypedMessage<T> messageBeingWrapped);
	public abstract <T> void set(String key,T datum);
	public abstract void setData(Data data) ;
	public abstract void setModule(Module module);
	
	public static PsmmFactory seekFactory(Messages.Type type){
		return factoryPool.seekFactory(type);
	}
	

	
}
