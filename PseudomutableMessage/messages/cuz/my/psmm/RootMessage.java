package cuz.my.psmm;

import java.util.Map;

@StateLess
enum RootMessage implements MessageAdaptorInterface<Object> {

	INSTANCE;
	private static final long serialVersionUID = 1L;

	// -------------------utility behavior:

	@SuppressWarnings("unchecked")
	public static <T> MessageAdaptorInterface<T> getInstance() {
		return (MessageAdaptorInterface<T>) INSTANCE;
	}

	// -------------------message behavior:
	@Override
	public Object get(String valuePath) {
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
	public Map<String, Object> getAll() {		
		return null;
	}

	@Override
	public MessageAdaptorInterface<Object> regress() {		
		return null;
	}

	@Override
	public Data readData() {		
		return null;
	}
	// ---------------------factory behaviors:

	@Override
	public AbstractRawMessage<Object> set(String key, Object datum) {		
		return null;
	}

	@Override
	public AbstractRawMessage<Object> raw() {		
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, String value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Integer value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Short value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Long value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Boolean value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Float value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Double value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Character value) {		
		return null;
	}

	@Override
	public UntypedRawMessage set(String key, Byte value) {
		return null;
	}

}
