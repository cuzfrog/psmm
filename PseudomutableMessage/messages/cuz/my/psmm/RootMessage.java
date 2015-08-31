package cuz.my.psmm;

import java.util.Map;



@StateLess
final class RootMessage implements TMessage<Object> {
	
	private static final RootMessage instance=new RootMessage();
	
	private RootMessage(){}
	
	//-------------------utility behavior:
	@SuppressWarnings("unchecked")
	public static <T> TMessage<T> getInstance(){
		return (TMessage<T>) instance;
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

	
	//factory behaviors:

	@Override
	public TypedRawMessage<Object> set(String key, Object datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedRawMessage<Object> raw() {
		// TODO Auto-generated method stub
		return null;
	}



}
