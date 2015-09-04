package cuz.my.psmm;

import java.util.Map;



@StateLess
final class RootMessage implements Message<Object> {
	
	private RootMessage() {
	}

	private static final Message<Object> instance=new RootMessage();
	
	
	//-------------------utility behavior:

	@SuppressWarnings("unchecked")
	public static <T> Message<T> getInstance(){
		return   (Message<T>) instance;
	}
	//-------------------message behavior:
	@Override
	public Object get(String valuePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer depth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public byte[] getSignature() {
		// TODO Auto-generated method stub
		return new byte[]{Integer.valueOf(0).byteValue()};
	}

	@Override
	public Map<String,Object> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Message<Object> regress() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//---------------------factory behaviors:

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
	public AbstractRawMessage<Object> set(String key, int value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, short value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, long value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, boolean value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, float value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, double value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, char value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, byte value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractRawMessage<Object> set(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
