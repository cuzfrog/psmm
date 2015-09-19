package com.github.cuzfrog.psmm;

import java.util.Map;

import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.StateLess;

@StateLess
enum RootMessage implements Message<Object, Object> {

	INSTANCE;
	private static final long serialVersionUID = 1L;

	// -------------------utility behavior:

	@SuppressWarnings("unchecked")
	public static <K, T> Message<K, T> getInstance() {
		return (Message<K, T>) INSTANCE;
	}

	// -------------------message behavior:
	@Override
	public Object get(Object valuePath) {
		return null;
	}

	@Override
	public int depth() {
		return 0;
	}

	@Override
	public byte[] getSignature() {
		return new byte[] { Integer.valueOf(0).byteValue() };
	}

	@Override
	public Map<Object, Object> getAll() {
		return null;
	}

	@Override
	public Map<String, Object> getUntypedAll() {
		return null;
	}

	@Override
	public Message<Object, Object> regress() {
		return null;
	}

	@Override
	public Data readData() {
		return null;
	}
	// ---------------------factory behaviors:

	@Override
	public AbstractBuilder<Object, Object> set(Object key, Object datum) {
		return null;
	}

	@Override
	public AbstractBuilder<Object, Object> builder() {
		return null;
	}

	@Override
	public AbstractBuilder<Object, Object> set(String key, String value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Integer value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Short value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Long value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Boolean value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Float value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Double value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Character value) {
		return null;
	}

	@Override
	public UBuilder set(String key, Byte value) {
		return null;
	}

	@Override
	public Object get(String key) {
		return null;
	}

}
