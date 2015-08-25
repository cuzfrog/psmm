package cuz.psmm;

import cuz.psmm.accessoaries.Configuration;
import cuz.psmm.messages.RootMessage;

public final class Messages {
	private static FactoryPool factoryPool = new ThreadFactoryPool(
			new Configuration());

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
		return new RawMessageImpl<T>(factoryPool.seekFactory(type), rootMessage);
	}

	static <T> RawMessage<T> fetch(Type type, Message<T> messageBeingWrapped) {
		return new RawMessageImpl<T>(factoryPool.seekFactory(type),
				messageBeingWrapped);
	}

	public static enum Type {
		LINKED_MAP, FLAT_MAP
	}
}
