package cuz.my.psmm;

/**
 * Do nothing.
 * @author Cause Chung
 *
 */
class NullMessagePool implements MessagePool {

	@Override
	public <T> MessageAdaptorInterface<T> get(Signature signature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Signature signature, MessageAdaptorInterface<?> message) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(Signature signature) {
		// TODO Auto-generated method stub
		return false;
	}

}
