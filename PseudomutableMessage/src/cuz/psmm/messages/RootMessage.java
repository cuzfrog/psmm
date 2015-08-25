package cuz.psmm.messages;

import java.util.Map;

import cuz.psmm.Message;
import cuz.psmm.RawMessage;




public class RootMessage implements Message<Object> {
	
	private static RootMessage instance=new RootMessage();
	
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
		return null;
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
