package cuz.my.psmm;

import java.util.HashMap;
import java.util.Map;

import cuz.my.psmm.data.Data;
import cuz.my.psmm.exceptions.PsmmCannotRegressExeption;

/**
 * In this abstract message, only signature has not been implemented.
 * 
 * @author cuzfrog
 *
 * @param <T>
 *            The Message<T> it contains.
 */
abstract class AbstractMessage<T> implements Message<T> {

	protected final Message<T> parent;
	protected final Data data;
	protected final Integer depth;
	protected final Messages.Style type;

	protected AbstractMessage(Messages.Style type, Message<T> parent, Data data) {
		super();
		this.parent = parent;
		this.data = data;
		this.depth = parent.depth() + 1;
		this.type = type;

	}

	@Override
	public Integer depth() {
		// TODO Auto-generated method stub
		return depth;
	}

	@Override
	public byte[] getSignature() {
		return new byte[] { Integer.valueOf(0).byteValue() };
	}

	@Override
	public T get(String key) {
		// TODO Auto-generated method stub
		T result = null;
		if ((result = data.get(key)) == null) {
			result = parent.get(key);
		}
		return result;
	}

	@Override
	public Map<String, T> getAll() {
		// TODO Auto-generated method stub
		Map<String, T> resultMap;
		Map<String, T> parentResult;
		if ((parentResult = parent.getAll()) != null) {
			resultMap = parentResult;
		} else {
			resultMap = new HashMap<>();
		}
		parentResult = data.getAll();
		resultMap.putAll(parentResult);
		return resultMap;
	}

	// ------------factory behaviors:
	@Override
	public AbstractRawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, datum);
	}

	@Override
	public AbstractRawMessage<T> raw() {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this);
	}
	// ------------UntypedMessage behaviors:

	@Override
	public Message<T> regress() throws PsmmCannotRegressExeption {
		// TODO Auto-generated method stub
		if (depth.equals(1)) {
			throw new PsmmCannotRegressExeption();
		}
		return parent;
	}

	@Override
	public AbstractRawMessage<T> set(String key, int value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, short value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, long value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, boolean value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, float value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, double value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, char value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, byte value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

	@Override
	public AbstractRawMessage<T> set(String key, String value) {
		// TODO Auto-generated method stub
		return PsmmSystem.fetchRaw(this.type, this).set(key, value);
	}

}
