package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.PsmmFactory;

abstract class AbstractBuilder<K,T> implements TBuilder<K,T>, UBuilder {

	private final PsmmFactory psmmFactory;
	private Message<K,T> messageBeingWrapped;

	protected AbstractBuilder(PsmmFactory psmmFactory) {
		super();
		this.psmmFactory = psmmFactory;
	}

	void setMessageBeingWrapped(Message<K,T> messageBeingWrapped) {
		this.messageBeingWrapped = messageBeingWrapped;
	}

	@Override
	public Message<K,T> build() {
		return (Message<K, T>) psmmFactory.commit(messageBeingWrapped);
	}

	@Override
	public AbstractBuilder<K,T> set(K key, T datum) {
		psmmFactory.set(key, datum);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Integer value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Short value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Long value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Boolean value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Float value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Double value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Character value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, Byte value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,T> set(String key, String value) {
		psmmFactory.set(key, value);
		return this;
	}

}