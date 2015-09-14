package com.github.cuzfrog.psmm;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.cuzfrog.psmm.exceptions.PsmmReachMaxDepthException;

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
	private int factoryPoolCheckInterval;
	private volatile ScheduledExecutorService lastExecutor;

	private PsmmSystem(PsmmConfiguration config) {
		switch (config.getFactoryPoolChoseType()) {
		case MAP:
			factoryPool = new MapFactoryPool(config);
			break;
		case NULL:
			factoryPool = new NullFactoryPool(config);
			break;
		default:
			break;
		}
		messageMaxDepth = config.getMessageMaxDepth();
		factoryPoolCheckInterval = config.getFactoryPoolCheckInterval();
		checkFactoryService();
	}

	private static void trimFactory() {
		instance.factoryPool.checkAndTrim(instance.factoryPoolCheckInterval);
	}

	private void checkFactoryService() {
		// check and trim factory
		if (this.lastExecutor != null) {
			lastExecutor.shutdown();
			// for re-initiation
		}
		if (factoryPoolCheckInterval > 0) {
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleWithFixedDelay(new Runnable() {

				@Override
				public void run() {
					// periodically do:
					PsmmSystem.trimFactory();
				}
			}, factoryPoolCheckInterval, factoryPoolCheckInterval, TimeUnit.SECONDS);
			lastExecutor = scheduler;
		}
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
	static <T> AbstractRawMessage<T> fetchRaw(Messages.Style type, Message<T> messageBeingWrapped) {
		if (messageBeingWrapped.depth() >= instance.messageMaxDepth) {
			throw new PsmmReachMaxDepthException();
		}
		PsmmFactory factory = PsmmSystem.seekFactory(type);
		AbstractRawMessage<T> rawMessage = factory.getRawMessage();
		rawMessage.setMessageBeingWrapped(messageBeingWrapped);
		return rawMessage;
	}

	static <T> Message<T> getRootMessage() {
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
	static <T> Message<T> getConcretMessage(Messages.Style type, Message<T> messageBeingWrapped, Data data) {
		return new FreeMessage<>(type, messageBeingWrapped, data);
	}

	// factory bridge:
	static PsmmFactory seekFactory(Messages.Style type) {
		return instance.factoryPool.seekFactory(type);
	}
}
