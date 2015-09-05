package cuz.my.psmm;

import cuz.my.psmm.PsmmFactory;

abstract class AbstractRawMessage<T> implements TypedRawMessage<T>,UntypedRawMessage {

	protected final PsmmFactory psmmFactory;
	protected AbstractRawMessage(PsmmFactory psmmFactory) {
		super();
		this.psmmFactory = psmmFactory;
	}

	protected Message<T> messageBeingWrapped;

	@Override
	public AbstractMessage<T> cook() {
		// TODO Auto-generated method stub
		return   (AbstractMessage<T>) psmmFactory.commit(messageBeingWrapped);
	}

	void setMessageBeingWrapped(Message<T> messageBeingWrapped) {
		this.messageBeingWrapped = messageBeingWrapped;
	}
	
	@Override
	public AbstractRawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, datum);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Integer value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Short value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Long value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Boolean value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Float value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Double value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Character value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Byte value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, String value) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, value);
		return this;
	}



	

}