package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.PsmmFactory;

abstract class AbstractBuilder<K,V> implements TBuilder<K,V>, UBuilder {

	private final PsmmFactory psmmFactory;
	private Message<K,V> messageBeingWrapped;

	protected AbstractBuilder(PsmmFactory psmmFactory) {
		super();
		this.psmmFactory = psmmFactory;
	}

	void setMessageBeingWrapped(Message<K,V> messageBeingWrapped) {
		this.messageBeingWrapped = messageBeingWrapped;
	}

	@Override
	public Message<K,V> build() {
		return psmmFactory.commit(messageBeingWrapped);
	}

	@Override
	public AbstractBuilder<K,V> set(K key, V datum) {
		psmmFactory.set(key, datum);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Integer value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Short value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Long value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Boolean value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Float value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Double value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Character value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Byte value) {
		psmmFactory.set(key, value);
		return this;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, String value) {
		psmmFactory.set(key, value);
		return this;
	}

}