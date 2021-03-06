package cuz.my.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class MapMessagePool implements MessagePool {

	private Map<Signature, MessageAdaptorInterface<?>> messagePool;

	MapMessagePool(int initialSize) {
		this.messagePool = new ConcurrentHashMap<>((int) Math.ceil(initialSize / 0.75));
	}

	@Override
	public boolean check(Signature signature){
		return messagePool.containsKey(signature);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> MessageAdaptorInterface<T> get(Signature signature) {
		// TODO Auto-generated method stub
		return (MessageAdaptorInterface<T>) messagePool.get(signature);
	}

	@Override
	public void put(Signature signature, MessageAdaptorInterface<?> message) {
		// TODO Auto-generated method stub
		messagePool.put(signature, message);
		//System.out.println("put! messagePoll size:"+messagePool.size());
	}

}
