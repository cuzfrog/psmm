package cuz.my.psmm;

/**
 * Class that manages FactoryPool and MessagePool and provides static
 * methods.
 * 
 * <p>
 * Before using Psmm, You need to explicitly call static methods
 * {@link PsmmSystem#initiate()} and
 * {@link PsmmSystem#initiate(PsmmConfiguration)} to create PsmmSystem instance,
 * or there'll be NullPointerException. It doesn't support lazy initiation, due
 * to performance concerns.
 * <p>
 * Note: this is not singleton class, careful to initiate.
 * 
 * @author Cause Chung
 * @see PsmmConfiguration
 */
public final class PsmmSystem {

	// singleton reference
	private static PsmmSystem instance;

	// pool members:
	private FactoryPool factoryPool;
	private MessagePool messagePool;

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
			MessageAdaptorInterface<T> messageBeingWrapped) {
		PsmmFactory factory = PsmmSystem.seekFactory(type);
		AbstractRawMessage<T> rawMessage = factory.getRawMessage();
		rawMessage.setMessageBeingWrapped(messageBeingWrapped);
		return rawMessage;
	}

	@SuppressWarnings("unchecked")
	static <T> MessageAdaptorInterface<T> seekMessage(Signature signature) {
		return (MessageAdaptorInterface<T>) instance.messagePool.get(signature);
	}

	static <T> MessageAdaptorInterface<T> getRootMessage() {
		return RootMessage.getInstance();
	}

	/**
	 * Create and return a uncached message. 
	 * 
	 * @param type
	 * @param messageBeingWrapped
	 * @param data
	 * @return a new cached message
	 */
	static <T> MessageAdaptorInterface<T> getConcretMessage(Messages.Style type,
			MessageAdaptorInterface<T> messageBeingWrapped, Data data) {
		return new UncachedMessage<>(type, messageBeingWrapped, data);
	}

	/**
	 * Create and return a cached message. An
	 * instance will be created and put into message pool.
	 * 
	 * @param type
	 * @param messageBeingWrapped
	 * @param data
	 * @param signature message's pseudo-unique signature that for pool check
	 * @return a new cached message
	 */
	static <T> MessageAdaptorInterface<T> getConcretMessage(Messages.Style type,
			MessageAdaptorInterface<T> messageBeingWrapped, Data data, Signature signature) {
		MessageAdaptorInterface<T> message = new CachedMessage<>(type, messageBeingWrapped,
				data, signature);
		instance.messagePool.put(signature, message);
		return message;
	}

	// factory bridge:
	static PsmmFactory seekFactory(Messages.Style type) {
		return instance.factoryPool.seekFactory(type);
	}
}
