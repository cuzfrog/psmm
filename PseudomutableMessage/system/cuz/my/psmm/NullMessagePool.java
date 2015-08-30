package cuz.my.psmm;

/**
 * Do nothing.
 * @author cuzfrog
 *
 */
class NullMessagePool implements MessagePool {

	@Override
	public <T> TMessage<T> get(Signature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Signature signature, TMessage<?> message) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(Signature signature) {
		// TODO Auto-generated method stub
		return false;
	}

}
