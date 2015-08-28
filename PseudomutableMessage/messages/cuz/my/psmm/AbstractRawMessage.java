package cuz.my.psmm;

import cuz.my.psmm.PsmmFactory;

abstract class AbstractRawMessage<T> implements TypedRawMessage<T>,UntypedRawMessage {

	protected PsmmFactory psmmFactory;
	protected TMessage<T> messageBeingWrapped;

	@Override
	public AbstractMessage<T> cook() {
		// TODO Auto-generated method stub
		return   (AbstractMessage<T>) psmmFactory.commit(messageBeingWrapped);
	}

	@Override
	public TypedRawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, datum);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, int value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, short value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, long value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, boolean value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, float value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, double value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, char value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, byte value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public UntypedRawMessage set(String key, String value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

}