package cuz.psmm.message;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.factory.datastructure.DataStructure;
/**
 * In this abstract message, only signature has not been implemented.
 * @author cuzfrog
 *
 * @param <T> The Message<T> it contains.
 */
abstract class AbstractMessage<T> extends Message<T> {

	protected final Message<T> parent;
	protected final DataStructure data;
	protected final Integer depth;
	protected final Message.Type type;

	protected AbstractMessage(Message.Type type, Message<T> parent,
			DataStructure data) {
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
	public RawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		return MessageHelper.fetch(this.type, this).set(key, datum);
	}

}
