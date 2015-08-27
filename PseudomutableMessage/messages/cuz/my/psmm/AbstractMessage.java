package cuz.my.psmm;

import java.util.HashMap;
import java.util.Map;

import cuz.my.psmm.data.Data;
/**
 * In this abstract message, only signature has not been implemented.
 * @author cuzfrog
 *
 * @param <T> The Message<T> it contains.
 */
abstract class AbstractMessage<T>  implements TypedMessage<T>,UntypedMessage {

	protected final TypedMessage<T> parent;
	protected final Data data;
	protected final Integer depth;
	protected final Messages.Type type;

	protected AbstractMessage(Messages.Type type, TypedMessage<T> parent,
			Data data) {
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
	public Map<String,T> getAll() {
		// TODO Auto-generated method stub
		Map<String, T> resultMap;
		Map<String, T> parentResult;
		if ((parentResult = parent.getAll()) != null) {
			resultMap = parentResult;
		} else {
			resultMap = new HashMap<>();
		}
		parentResult=data.getAll();
		resultMap.putAll(parentResult);
		return resultMap;
	}

	// ------------factory behaviors:
	@Override
	public TypedRawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, datum);
	}
	// ------------UntypedMessage behaviors:

	@Override
	public UntypedRawMessage set(String key, int value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, short value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, long value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, boolean value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, float value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, double value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, char value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, byte value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	@Override
	public UntypedRawMessage set(String key, String value) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, value);
	}

	
}
