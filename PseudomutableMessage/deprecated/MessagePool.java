package cuz.my.psmm;

interface MessagePool {

	boolean check(Signature signature);
	<T> MessageAdaptorInterface<T> get(Signature signature);
	
	void put(Signature signature,MessageAdaptorInterface<?> message);
}
