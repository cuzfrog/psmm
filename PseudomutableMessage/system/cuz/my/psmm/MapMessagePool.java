package cuz.my.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class MapMessagePool implements MessagePool {

	private Map<Signature, TMessage<?>> messagePool;

	MapMessagePool(int initialSize) {
		this.messagePool = new ConcurrentHashMap<>((int) Math.ceil(initialSize / 0.75));
	}

	@Override
	public boolean check(Signature signature){
		return messagePool.containsKey(signature);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> TMessage<T> get(Signature signature) {
		// TODO Auto-generated method stub
		return (TMessage<T>) messagePool.get(signature);
	}

	@Override
	public void put(Signature signature, AbstractMessage<?> message) {
		// TODO Auto-generated method stub
		messagePool.put(signature, message);
		//System.out.println("put! messagePoll size:"+messagePool.size());
	}

}
