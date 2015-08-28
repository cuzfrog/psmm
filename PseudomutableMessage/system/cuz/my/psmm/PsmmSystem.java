package cuz.my.psmm;

import cuz.my.psmm.data.Data;

/**
 * Singleton class that manages FactoryPool and MessagePool and provides static
 * methods.
 * 
 * <p>
 * Before using Psmm, You need to explicitly call static methods
 * {@link PsmmSystem#initiate()} and
 * {@link PsmmSystem#initiate(PsmmConfiguration)} to create PsmmSystem instance,
 * or there'll be NullPointerException. It doesn't support lazy initiation, due
 * to performance concerns.
 * 
 * @author cuzfrog
 * @see PsmmConfiguration
 */
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

	/**
	 * Initiate with specified Configuration.
	 * 
	 * @param config
	 *            user defined Configuration.
	 * @see PsmmConfiguration
	 */
	public static void initiate(PsmmConfiguration config) {
		if (instance == null) {
			instance = new PsmmSystem(config);
		}
	}

	/**
	 * Initiate with default Configuration.
	 */
	public static void initiate() {
		initiate(new PsmmConfiguration()); // default initiate
	}

	// utility methods:----------------
	// create raw message:
	static <T> AbstractRawMessage<T> fetchRaw(Messages.Type type, TMessage<T> messageBeingWrapped) {
		PsmmFactory factory = PsmmSystem.seekFactory(type);
		AbstractRawMessage<T> rawMessage = factory.getRawMessage();
		rawMessage.setMessageBeingWrapped(messageBeingWrapped);
		return rawMessage;
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
	static <T> TMessage<T> getConcretMessage(Messages.Type type, TMessage<T> messageBeingWrapped, Data data, Signature signature) {

		TMessage<T> message = new CachedMessage<>(type, messageBeingWrapped, data, signature);
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
	static <T> TMessage<T> getConcretMessage(Messages.Type type, TMessage<T> messageBeingWrapped, Data data) {
		return new UncachedMessage<>(type, messageBeingWrapped, data);
	}

	// factory bridge:
	static PsmmFactory seekFactory(Messages.Type type) {
		return factoryPool.seekFactory(type);
	}
}
