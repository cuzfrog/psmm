package cuz.my.psmm;

import java.util.Map;

import cuz.my.psmm.data.Data;

@StateLess
final class RootMessage implements Message<Object> {

	private RootMessage() {
	}

	private static final Message<Object> instance = new RootMessage();

	// -------------------utility behavior:

	@SuppressWarnings("unchecked")
	public static <T> Message<T> getInstance() {
		return (Message<T>) instance;
	}

	// -------------------message behavior:
	@Override
	public Object get(String valuePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int depth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getSignature() {
		// TODO Auto-generated method stub
		return new byte[] { Integer.valueOf(0).byteValue() };
	}

	@Override
	public Map<String, Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message<Object> regress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Data readData() {
		// TODO Auto-generated method stub
		return null;
	}
	// ---------------------factory behaviors:

	@Override
	public AbstractRawMessage<Object> set(String key, Object datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> raw() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Integer value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Short value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Boolean value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Float value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Double value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Character value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Byte value) {
		// TODO Auto-generated method stub
		return null;
	}

}
