package cuz.my.psmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuz.my.psmm.data.Data;

/**
 * Class for configuring psmm when initiating.
 * <p>
 * Methods to setup (see each for details):<br>
 * {@link #PsmmConfiguration(Integer)} to set message pool size.<br>
 * {@link #PsmmConfiguration(FactoryPoolType)} to set factory pool type.<br>
 * {@link #PsmmConfiguration(Integer, FactoryPoolType)} to set above together.
 * <br>
 * {@link #setFactoryPoolSize(Integer)} to set message pool size.<br>
 * Psmm will create a default one, if you don't specify it.
 * <p>
 * Default message pool size configuration is 0, which means there will not be
 * any message being cached. See {@link MessagePool} for details. Default
 * factory pool size configuration is 16, taking MapFactoryPool as an example,
 * this is according to how many threads ID there'll be.
 * <p>
 * However these configurations are not vital. They're for optimization like big
 * enough pool size to prevent Map rehash.
 * 
 * @author cuzfrog
 *
 */
@NotThreadSafe
public class PsmmConfiguration {

	public static enum FactoryPoolType {
		MAP
	}

	// constants:
	private static final Integer DEFAULT_MESSAGE_POOL_SIZE = 0;
	private static final FactoryPoolType DEFAULT_FACTORYPOOL_TYPE = FactoryPoolType.MAP;
	private static final Integer DEFAULT_FACTORY_POOL_SIZE = 16;

	// configuration members:
	private Integer messagePoolSize;
	private Map<String, Module> customModules = new HashMap<>();
	private List<Data> customData = new ArrayList<>();
	private FactoryPoolType factoryPoolChoseType;
	private Integer factoryPoolSize = DEFAULT_FACTORY_POOL_SIZE;

	// constructors:
	PsmmConfiguration() {
		this(DEFAULT_MESSAGE_POOL_SIZE);
	}

	/**
	 * Create PsmmConfiguration.
	 * 
	 * @param messagePoolSize
	 */
	public PsmmConfiguration(Integer messagePoolSize) {
		this(messagePoolSize, DEFAULT_FACTORYPOOL_TYPE);
	}

	/**
	 * Create PsmmConfiguration.
	 * 
	 * @param factoryPoolChoseType
	 */
	public PsmmConfiguration(FactoryPoolType factoryPoolChoseType) {
		this(DEFAULT_MESSAGE_POOL_SIZE, factoryPoolChoseType);
	}

	/**
	 * Create PsmmConfiguration.
	 * 
	 * @param messagePoolSize
	 * @param factoryPoolChoseType
	 */
	public PsmmConfiguration(Integer messagePoolSize, FactoryPoolType factoryPoolChoseType) {
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
	void addModule(Module module) {
		customModules.put(module.getName(), module);
	}

	void addData(Data data) {
		customData.add(data);
	}

	public Integer getMessagePoolSize() {
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

	public Integer getFactoryPoolSize() {
		return factoryPoolSize;
	}

	public void setFactoryPoolSize(Integer factoryPoolSize) {
		this.factoryPoolSize = factoryPoolSize;
	}

}
