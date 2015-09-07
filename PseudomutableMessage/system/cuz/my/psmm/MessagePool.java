package cuz.my.psmm;


interface MessagePool {

	boolean check(Checkable signature);
	<T> Message<T> get(Checkable signature);
	
	void put(Checkable signature,Message<?> message);
}
