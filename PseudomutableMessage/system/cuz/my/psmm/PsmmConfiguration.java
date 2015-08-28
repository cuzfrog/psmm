package cuz.my.psmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuz.my.psmm.accessories.NotThreadSafe;
import cuz.my.psmm.data.Data;

/**
 * Class for configure psmm when initiating.
 * <p>
 * Psmm will create a default one, if you don't specify it.
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
	public PsmmConfiguration() {
		this(DEFAULT_MESSAGE_POOL_SIZE);
	}

	public PsmmConfiguration(Integer messagePoolSize) {
		this(messagePoolSize, DEFAULT_FACTORYPOOL_TYPE);
	}

	public PsmmConfiguration(FactoryPoolType factoryPoolChoseType) {
		this(DEFAULT_MESSAGE_POOL_SIZE, factoryPoolChoseType);
	}

	public PsmmConfiguration(Integer messagePoolSize,
			FactoryPoolType factoryPoolChoseType) {
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
	public void addModule(Module module) {
		customModules.put(module.getName(), module);
	}

	public void addData(Data data) {
		customData.add(data);
	}

	public Integer getMessagePoolSize() {
		return messagePoolSize;
	}

	public Map<String, Module> getCustomModules() {
		return customModules;
	}

	public List<Data> getCustomData() {
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
