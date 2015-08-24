package cuz.psmm;

interface PsmmFactory<T> extends RawMessage<T>{
	
	 PsmmFactory<T> wrap(Message<T> message);
	
}
