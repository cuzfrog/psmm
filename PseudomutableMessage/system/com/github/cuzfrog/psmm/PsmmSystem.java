package com.github.cuzfrog.psmm;

/**
 * Class that manages FactoryPool and MessagePool and provides static methods.
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

	// self reference
	private static PsmmSystem instance;

	// members:
	private volatile FactoryPool factoryPool;
	private int messageMaxDepth;

	private PsmmSystem(PsmmConfiguration config) {
		switch (config.getFactoryPoolChoseType()) {
		case NULL:
			factoryPool = new NullFactoryPool(config);
			break;
		default:
			factoryPool = new ThreadLocalFactoryPool(config);
			break;
		}
		messageMaxDepth = config.getMessageMaxDepth();
	}

	/**
	 * Initiate with specified Configuration.
	 * 
	 * @param config
	 *            user defined Configuration.
	 * @see PsmmConfiguration
	 */
	public static synchronized void initiate(PsmmConfiguration config) {
		instance = new PsmmSystem(config);
	}

	/**
	 * Initiate with default Configuration.
	 */
	public static synchronized void initiate() {
		initiate(new PsmmConfiguration()); // default initiate
	}

	// utility methods:----------------
	// create raw message:
	static <K,T> AbstractBuilder<K,T> fetchRaw(Messages.Style type, Message<K,T> messageBeingWrapped) {
		if (messageBeingWrapped.depth() >= instance.messageMaxDepth) {
			throw new  IllegalStateException("Message chain too long. Depth:"+messageBeingWrapped.depth());
		}
		PsmmFactory factory = PsmmSystem.seekFactory(type);
		AbstractBuilder<K,T> rawMessage = factory.getRawMessage();
		rawMessage.setMessageBeingWrapped(messageBeingWrapped);
		return rawMessage;
	}

	static <K,T> Message<K,T> getRootMessage() {
		return RootMessage.getInstance();
	}

	/**
	 * Create and return a uncached message.
	 * 
	 * @param style
	 * @param messageBeingWrapped
	 * @param data
	 * @return a new cached message
	 */
	static <K,T> Message<K,T> getConcretMessage(Messages.Style style, Message<K,T> messageBeingWrapped, Data data) {
		if (style.isValue()) {
			return new ValueMessage<>(style, messageBeingWrapped, data);
		} else {
			return new UniqueMessage<>(style, messageBeingWrapped, data);
		}
	}

	// factory bridge:
	static PsmmFactory seekFactory(Messages.Style type) {
		return instance.factoryPool.seekFactory(type);
	}
}
