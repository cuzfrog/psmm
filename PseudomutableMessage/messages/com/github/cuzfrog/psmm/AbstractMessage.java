package com.github.cuzfrog.psmm;

import java.util.HashMap;
import java.util.Map;

/**
 * In this abstract message, only signature has not been implemented.
 * 
 * @author Cause Chung
 *
 */
abstract class AbstractMessage<K,V> implements Message<K,V> {

	protected final Message<K,V> parent;
	protected final Data data;
	protected final int depth;
	protected final Messages.Style type;

	protected AbstractMessage(Messages.Style type, Message<K,V> parent, Data data) {
		super();
		this.parent = parent;
		this.data = data;
		this.depth = parent.depth() + 1;
		this.type = type;

	}

	@Override
	public int depth() {
		return depth;
	}

	@Override
	public byte[] getSignature() {
		return new byte[] { Integer.valueOf(0).byteValue() };
	}

	@Override
	public V get(K key) {
		V result = null;
		if ((result = data.get(key)) == null) {
			result = parent.get(key);
		}
		return result;
	}

	@Override
	public Map<K, V> getAll() {
		Map<K, V> resultMap;
		Map<K, V> parentResult = parent.getAll();
		if (parentResult != null) {
			resultMap = parentResult;
		} else {
			resultMap = new HashMap<>();
			// when reaches root, create a new map to return.
		}
		parentResult = data.getAll();
		resultMap.putAll(parentResult);
		return resultMap; // return a new instance from root.
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getUntypedAll() {
		return (Map<String, Object>) getAll();
	}

	// package interface method to facilitate internal function.
	@Override
	public Data readData() {
		Data resultData;
		Data parentData = parent.readData();
		if (parentData != null) {
			resultData=parentData.merge(this.data);
		} else {
			resultData = Data.newData(data.getStructure());
			// when reaches root, create a new to return.
			resultData.merge(this.data);
		}
		return resultData; 
		// return a new instance from the one that contains the root message.
	}

	// ------------factory behaviors:
	@Override
	public AbstractBuilder<K,V> set(K key, V datum) {
		return PsmmSystem.fetchBuilder(this.type, this).set(key, datum);
	}

	@Override
	public AbstractBuilder<K,V> builder() {
		return PsmmSystem.fetchBuilder(this.type, this);
	}
	
	@Override
	public Message<K,V> regress() {
		if (depth <= 1) {
			return null;
		}
		return parent;
	}
	
	
	
	
	// ------------UntypedMessage behaviors:
	@Override
	public Object get(String key) {
		Object result = null;
		if ((result = data.get(key)) == null) {
			result = parent.get(key);
		}
		return result;
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Integer value) {
		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Short value) {
		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Long value) {
		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Boolean value) {
		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Float value) {

		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Double value) {

		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Character value) {

		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, Byte value) {
		return builder().set(key, value);
	}

	@Override
	public AbstractBuilder<K,V> set(String key, String value) {
		return builder().set(key, value);
	}



}
