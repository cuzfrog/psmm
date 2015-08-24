package cuz.psmm;

import cuz.psmm.impl.FlatPsmmFactory;
import cuz.psmm.impl.LinkedPsmmFactory;

abstract class PsmmFactory<T> implements RawMessage<T>{
	
	public abstract PsmmFactory<T> wrap(Message<T> message);
	
	
	public static <W> PsmmFactory<W> seekFactory(Messages.Type type,Message<W> messageBeingWrapped){
		PsmmFactory<W> psmmFactory=null;
		switch(type){
		case FLAT_MAP:
			psmmFactory=new FlatPsmmFactory<W>();
			break;
		case FLAT_SINGLE:
			break;
		case LINKED_MAP:
			psmmFactory=new LinkedPsmmFactory<W>();
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
