package cuz.my.psmm;


/**
 * Do nothing.
 * @author cuzfrog
 *
 */
class NullMessagePool implements MessagePool {

	@Override
	public <T> Message<T> get(Checkable signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Checkable signature, Message<?> message) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(Checkable signature) {
		// TODO Auto-generated method stub
		return false;
	}

}
