package cuz.my.psmm;

import cuz.my.psmm.data.Data;

/**
 * Internal helper class that manages FactoryPool and MessagePool and provides
 * static methods.
 * <p>
 * Not strictly singleton.
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
	private FactoryPool factoryPool;
	private MessagePool messagePool;

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
		instance = new PsmmSystem(config);
	}

	/**
	 * Initiate with default Configuration.
	 */
	public static void initiate() {
		initiate(new PsmmConfiguration()); // default initiate
	}

	// utility methods:----------------
	// create raw message:
	static <T> AbstractRawMessage<T> fetchRaw(Messages.Style type,
			Message<T> messageBeingWrapped) {
		PsmmFactory factory = PsmmSystem.seekFactory(type);
		AbstractRawMessage<T> rawMessage = factory.getRawMessage();
		rawMessage.setMessageBeingWrapped(messageBeingWrapped);
		return rawMessage;
	}

	@SuppressWarnings("unchecked")
	static <T> Message<T> seekMessage(Checkable signature) {
		return (Message<T>) instance.messagePool.get(signature);
	}

	static <T> Message<T> getRootMessage() {
		return RootMessage.getInstance();
	}

	/**
	 * Create and return a message. If its type indicates it's cached, a cached
	 * instance will be created and put into message pool.
	 * 
	 * @param type
	 * @param messageBeingWrapped
	 * @param data
	 * @return a new cached message
	 */
	static <T> Message<T> getConcretMessage(Messages.Style type,
			Message<T> messageBeingWrapped, Data data) {

		return new UncachedMessage<>(type, messageBeingWrapped, data);
	}

	static <T> Message<T> getConcretMessage(Messages.Style type,
			Message<T> messageBeingWrapped, Data data, Data signature) {

		Message<T> message = new CachedMessage<>(type, messageBeingWrapped,
				data, signature);
		instance.messagePool.put(message, message);
		return message;

	}

	// factory bridge:
	static PsmmFactory seekFactory(Messages.Style type) {
		return instance.factoryPool.seekFactory(type);
	}
}
