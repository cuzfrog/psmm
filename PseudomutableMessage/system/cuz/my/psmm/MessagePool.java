package cuz.my.psmm;

interface MessagePool {

	<T> TMessage<T> get(Signature signature);
	
	void put(Signature signature,TMessage<?> message);
}
