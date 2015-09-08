package cuz.my.psmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for configuring psmm when initiating.
 * <p>
 * Constructor or set-method both do configuration work. PsmmSystem will create
 * a default PsmmConfiguration if you don't specify one.
 * <p>
 * Default message pool initial size is 0, which means there will
 * not be any message being cached. Default factory pool initial size is
 * 16, this should be set more than the
 * threads ID there'll be.
 * <p>
 * However these configurations are not vital. They're for optimization, like big
 * enough pool size to prevent Map rehash.
 * 
 * @author Cause Chung
 *
 */
@NotThreadSafe
public class PsmmConfiguration {

	public enum FactoryPoolType {
		MAP
	}

	// constants:
	private static final int DEFAULT_MESSAGE_POOL_SIZE = 0;
	private static final FactoryPoolType DEFAULT_FACTORYPOOL_TYPE = FactoryPoolType.MAP;
	private static final int DEFAULT_FACTORY_POOL_SIZE = 16;

	// configuration members:
	private int messagePoolSize;
	private Map<String, Module> customModules = new HashMap<>();
	private List<Data> customData = new ArrayList<>();
	private FactoryPoolType factoryPoolChoseType;
	private int factoryPoolSize = DEFAULT_FACTORY_POOL_SIZE;

	// constructors:
	/**
	 * Default constructor.
	 * 
	 * <p>
	 * Provide a way to setup it after construction.
	 */
	public PsmmConfiguration() {
		this(DEFAULT_MESSAGE_POOL_SIZE);
	}

	/**
	 * Create PsmmConfiguration.
	 * 
	 * @param messagePoolSize
	 *            specify initial pool size.
	 */
	public PsmmConfiguration(int messagePoolSize) {
		this(messagePoolSize, DEFAULT_FACTORYPOOL_TYPE);
	}

	/**
	 * Create PsmmConfiguration.
	 * 
	 * @param factoryPoolChoseType
	 *            specify concrete factory pool.
	 */
	public PsmmConfiguration(FactoryPoolType factoryPoolChoseType) {
		this(DEFAULT_MESSAGE_POOL_SIZE, factoryPoolChoseType);
	}

	/**
	 * Create PsmmConfiguration.
	 * 
	 * @param messagePoolSize
	 *            specify initial pool size.
	 * @param factoryPoolChoseType
	 *            specify concrete factory pool.
	 */
	public PsmmConfiguration(int messagePoolSize, FactoryPoolType factoryPoolChoseType) {
		this.messagePoolSize = messagePoolSize;
		switch (factoryPoolChoseType) {
		case MAP:
			this.factoryPoolChoseType = factoryPoolChoseType;
			break;
		default:
			break;
		}

	}

	// custom methods:
	PsmmConfiguration addModule(Module module) {
		customModules.put(module.getName(), module);
		return this;
	}

	PsmmConfiguration addData(Data data) {
		customData.add(data);
		return this;
	}

	public int getMessagePoolSize() {
		return messagePoolSize;
	}

	Map<String, Module> getCustomModules() {
		return customModules;
	}

	List<Data> getCustomData() {
		return customData;
	}

	public FactoryPoolType getFactoryPoolChoseType() {
		return factoryPoolChoseType;
	}

	public int getFactoryPoolSize() {
		return factoryPoolSize;
	}

	public PsmmConfiguration setFactoryPoolSize(int factoryPoolSize) {
		this.factoryPoolSize = factoryPoolSize;
		return this;
	}

	public PsmmConfiguration setMessagePoolSize(int messagePoolSize) {
		this.messagePoolSize = messagePoolSize;
		return this;
	}

	public PsmmConfiguration setFactoryPoolChoseType(FactoryPoolType factoryPoolChoseType) {
		this.factoryPoolChoseType = factoryPoolChoseType;
		return this;
	}
}
