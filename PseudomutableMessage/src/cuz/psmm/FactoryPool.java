package cuz.psmm;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import cuz.psmm.Messages.Type;
import cuz.psmm.impl.FlatPsmmFactory;
import cuz.psmm.impl.LinkedPsmmFactory;
import cuz.psmm.utility.Configuration;

/**
 * 
 * @author cuzfrog
 *
 */
class FactoryPool {

	private volatile Boolean[] redLights;
	private PsmmFactory<?>[] pool;
	
	FactoryPool(Configuration config){
		
	}
	
	<T> PsmmFactory<T> seekFactory(Type type, Message<T> messageBeingWrapped) {
		// TODO Auto-generated method stub
		return this.createNew(type, messageBeingWrapped);
	}
	
	void returnFactory(Integer position){
		redLights[position]=false;
	}
	
	private <T> PsmmFactory<T> reuseOld(Type type, Message<T> messageBeingWrapped){
		PsmmFactory<T> psmmFactory=null;
		
	}

	private <T> PsmmFactory<T> createNew(Type type, Message<T> messageBeingWrapped){
		PsmmFactory<T> psmmFactory=null;
		switch(type){
		case FLAT_MAP:
			psmmFactory=new FlatPsmmFactory<T>();
			break;
		case FLAT_SINGLE:
			break;
		case LINKED_MAP:
			psmmFactory=new LinkedPsmmFactory<T>();
			break;
		case LINKED_SINGLE:
			break;
		default:
			break;
		}
		psmmFactory.wrap(messageBeingWrapped);
		return psmmFactory;
	}
	

}
