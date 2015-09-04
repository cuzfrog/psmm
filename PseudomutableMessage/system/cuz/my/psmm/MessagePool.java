package cuz.my.psmm;

interface MessagePool {

	boolean check(Signature signature);
	<T> Message<T> get(Signature signature);
	
	void put(Signature signature,Message<?> message);
}
