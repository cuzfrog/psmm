package cuz.my.psmm;

import cuz.my.psmm.data.Data;

public final class PsmmSystem {

	// singleton reference
	private static PsmmSystem instance;

	// pool members:
	private static FactoryPool factoryPool;
	private static MessagePool messagePool;

	// singleton behavior:
	private PsmmSystem(PsmmConfiguration config) {
		factoryPool = new MapFactoryPool(config.getFactoryPoolSize());
		if (config.getMessagePoolSize() > 0) {
			messagePool = new MapMessagePool(config.getMessagePoolSize());
		} else {
			messagePool = new NullMessagePool();
		}
	}

	public static void initiate(PsmmConfiguration config) {
		if (instance == null) {
			instance = new PsmmSystem(config);
		}
	}

	public static void initiate() {
		initiate(new PsmmConfiguration()); // default initiate
	}

	// utility methods:----------------
	// create raw message:
	static <T> AbstractRawMessage<T> fetch(Messages.Type type,
			TMessage<T> messageBeingWrapped) {
		return new RawMessageImpl<>(PsmmSystem.seekFactory(type),
				messageBeingWrapped);
	}

	@SuppressWarnings("unchecked")
	static <T> TMessage<T> seekMessage(Signature signature) {
		return (TMessage<T>) messagePool.get(signature);
	}

	static <T> TMessage<T> getRootMessage() {
		return RootMessage.getInstance();
	}

	/**
	 * Create and return a cached message. And put this message into message
	 * pool.
	 * 
	 * @param type
	 * @param messageBeingWrapped
	 * @param data
	 * @param signature
	 *            for this message.
	 * @return a new cached message
	 */
	static <T> TMessage<T> getConcretMessage(Messages.Type type,
			TMessage<T> messageBeingWrapped, Data data, Signature signature) {

		TMessage<T> message = new CachedMessage<>(type, messageBeingWrapped,
				data, signature);
		messagePool.put(signature, message);
		return message;

	}

	/**
	 * Create and return a uncached message.
	 * 
	 * @param type
	 * @param messageBeingWrapped
	 * @param data
	 * @return a new uncached message.
	 */
	static <T> TMessage<T> getConcretMessage(Messages.Type type,
			TMessage<T> messageBeingWrapped, Data data) {
		return new UncachedMessage<>(type, messageBeingWrapped, data);
	}

	// factory bridge:
	static PsmmFactory seekFactory(Messages.Type type) {
		return factoryPool.seekFactory(type);
	}
}
