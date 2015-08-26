package cuz.psmm.message;

import java.util.Map;

import cuz.psmm.accessories.StateLess;



@StateLess
final class RootMessage extends Message<Object> {
	
	private static final RootMessage instance=new RootMessage();
	
	private RootMessage(){}
	
	//-------------------utility behavior:
	@SuppressWarnings("unchecked")
	public static <T> Message<T> getInstance(){
		return (Message<T>) instance;
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
	public RawMessage<Object> set(String key, Object datum) {
		// TODO Auto-generated method stub
		return null;
	}



}
