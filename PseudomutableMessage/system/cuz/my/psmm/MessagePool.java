package cuz.my.psmm;

interface MessagePool {

	boolean check(Signature signature);
	<T> TMessage<T> get(Signature signature);
	
	void put(Signature signature,TMessage<?> message);
}
