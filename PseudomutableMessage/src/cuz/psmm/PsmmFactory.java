package cuz.psmm;

import cuz.psmm.impl.LinkedPsmmFactory;

interface PsmmFactory<T> extends RawMessage<T>{
	
	PsmmFactory<T> wrap(Message<T> message);
	
	
	static <W> PsmmFactory<W> seekFactory(Message.Type type,Message<W> messageBeingWrapped){
		//DEBUG:
		PsmmFactory<W> psmmFactory=new LinkedPsmmFactory<W>();
		
		
		psmmFactory.wrap(messageBeingWrapped);
		return psmmFactory;
		
	}
}
