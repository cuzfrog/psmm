package cuz.my.psmm;

import cuz.my.psmm.PsmmFactory;

abstract class AbstractRawMessage<T> implements TypedRawMessage<T>, UntypedRawMessage {

	protected final PsmmFactory psmmFactory;

	protected AbstractRawMessage(PsmmFactory psmmFactory) {
		super();
		this.psmmFactory = psmmFactory;
	}

	protected MessageAdaptorInterface<T> messageBeingWrapped;

	@Override
	public AbstractMessage<T> cook() {
		return (AbstractMessage<T>) psmmFactory.commit(messageBeingWrapped);
	}

	void setMessageBeingWrapped(MessageAdaptorInterface<T> messageBeingWrapped) {
		this.messageBeingWrapped = messageBeingWrapped;
	}

	@Override
	public AbstractRawMessage<T> set(String key, T datum) {
		psmmFactory.set(key, datum);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Integer value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Short value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Long value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Boolean value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Float value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Double value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Character value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Byte value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractRawMessage<T> set(String key, String value) {
		psmmFactory.set(key, value);
		return this;
	}

}