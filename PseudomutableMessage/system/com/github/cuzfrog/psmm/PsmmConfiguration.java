package com.github.cuzfrog.psmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for configuring psmm when initiating.
 * <p>
 * PsmmSystem will create a default PsmmConfiguration if you don't specify one.
 * <p>
 * Default message linked max depth is 256,  Note that long message chain could occupy
 * chunks of memory.
 * 
 * @author Cause Chung
 *
 */
@NotThreadSafe
public final class PsmmConfiguration {

	/**
	 * Factory pool type.
	 * 
	 * <p>Factory is what does the actual work for builder. 
	 * @author Cause Chung
	 *
	 */
	public enum FactoryPoolType {
		/** 
		 * Builder and factory are attached to thread using ThreadLocal.
		 * Every time calling a builder, builder and factory will be reused.
		 * This is default type.
		 */
		THREAD_LOCAL, 
		/**
		 * Every time calling a builder, new instance will be created.
		 */
		NULL
	}

	// constants:
	private static final FactoryPoolType DEFAULT_FACTORYPOOL_TYPE = FactoryPoolType.THREAD_LOCAL;

	private static final int DEFAULT_MESSAGE_MAX_DEPTH = 256;

	// configuration members:
	private Map<String, Module> customModules = new HashMap<>();
	private List<Data> customData = new ArrayList<>();
	private FactoryPoolType factoryPoolChoseType = DEFAULT_FACTORYPOOL_TYPE;
	private int messageMaxDepth = DEFAULT_MESSAGE_MAX_DEPTH;

	// custom methods:
	PsmmConfiguration addModule(Module module) {
		customModules.put(module.getName(), module);
		return this;
	}

	PsmmConfiguration addData(Data data) {
		customData.add(data);
		return this;
	}

	Map<String, Module> getCustomModules() {
		return customModules;
	}

	List<Data> getCustomData() {
		return customData;
	}

	/**
	 * Return the type of factory pool now set.
	 * 
	 * @return type of factory pool.
	 */
	public FactoryPoolType getFactoryPoolChoseType() {
		return factoryPoolChoseType;
	}

	/**
	 * Set factory pool type and return reference to self.
	 * 
	 * @param factoryPoolChoseType
	 * @return reference to self for cascading calling.
	 */
	public PsmmConfiguration setFactoryPoolChoseType(FactoryPoolType factoryPoolChoseType) {
		this.factoryPoolChoseType = factoryPoolChoseType;
		return this;
	}

	/**
	 * Return maximum depth of message chain.
	 * @return maximum depth of message chain.
	 */
	public int getMessageMaxDepth() {
		return messageMaxDepth;
	}

	/**
	 * Set maximum message chain depth.
	 * @param messageMaxDepth depth of message chain.
	 * @return reference to self for cascading calling.
	 * @throws PsmmInvalidConfigurationException if parameter set is less than 1.
	 */
	public PsmmConfiguration setMessageMaxDepth(int messageMaxDepth) {
		if(messageMaxDepth<1){
			throw new IllegalArgumentException("Message max depth must be more than 0");
		}
		this.messageMaxDepth = messageMaxDepth;
		return this;
	}
}
