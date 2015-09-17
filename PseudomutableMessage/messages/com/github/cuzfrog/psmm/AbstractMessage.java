package com.github.cuzfrog.psmm;

import java.util.HashMap;
import java.util.Map;

import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.exceptions.PsmmCannotRegressExeption;

/**
 * In this abstract message, only signature has not been implemented.
 * 
 * @author Cause Chung
 *
 * @param <T>
 *            parameter type.
 */
abstract class AbstractMessage<T> implements Message<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Message<T> parent;
	protected final Data data;
	protected final int depth;
	protected final Messages.Style type;

	protected AbstractMessage(Messages.Style type, Message<T> parent, Data data) {
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
	public T get(String key) {
		T result = null;
		if ((result = data.get(key)) == null) {
			result = parent.get(key);
		}
		return result;
	}

	@Override
	public Map<String, T> getAll() {
		Map<String, T> resultMap;
		Map<String, T> parentResult = parent.getAll();
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
	public AbstractRawMessage<T> set(String key, T datum) {
		return PsmmSystem.fetchRaw(this.type, this).set(key, datum);
	}

	@Override
	public AbstractRawMessage<T> raw() {
		return PsmmSystem.fetchRaw(this.type, this);
	}
	// ------------UntypedMessage behaviors:

	@Override
	public Message<T> regress() throws PsmmCannotRegressExeption {
		if (depth == 1) {
			throw new PsmmCannotRegressExeption();
		}
		return parent;
	}

	@Override
	public AbstractRawMessage<T> set(String key, Integer value) {
		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, Short value) {
		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, Long value) {
		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, Boolean value) {
		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, Float value) {

		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, Double value) {

		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, Character value) {

		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, Byte value) {
		return raw().set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, String value) {
		return raw().set(key, value);
	}



}