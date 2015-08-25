package cuz.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cuz.psmm.accessoaries.Configuration;
import cuz.psmm.messages.RootMessage;

public final class Messages {
	private static FactoryPool factoryPool = new ThreadFactoryPool(
			new Configuration());

	private static Map<byte[],Message<?>> messagePool =new ConcurrentHashMap<>(); 
	
	private Messages() {
	}

	/**
	 * 
	 * @param type
	 * @param c
	 * @return PsmmFactory as RawMessage.
	 */
	public static <T> RawMessage<T> create(Type type, Class<T> c) {

		Message<T> rootMessage = RootMessage.getInstance();
		return new RawMessageImpl<>(factoryPool.seekFactory(type), rootMessage);
	}

	
	static <T> RawMessage<T> fetch(Type type, Message<T> messageBeingWrapped) {
		return new RawMessageImpl<>(factoryPool.seekFactory(type),
				messageBeingWrapped);
	}

	@SuppressWarnings("unchecked")
	public <T> Message<T> seekMessage(byte[] signature){
		return (Message<T>) messagePool.get(signature);
	}
	
	public static enum Type {
		LINKED_MAP, FLAT_MAP
	}
}
