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
 * Default factory pool size is 16, this should be set more than the threads ID
 * there'll be.
 * @author Cause Chung
 *
 */
@NotThreadSafe
public class PsmmConfiguration {

	public enum FactoryPoolType {
		MAP,NULL
	}

	// constants:
	private static final FactoryPoolType DEFAULT_FACTORYPOOL_TYPE = FactoryPoolType.MAP;
	private static final int DEFAULT_FACTORY_POOL_SIZE = 16;

	// configuration members:
	private Map<String, Module> customModules = new HashMap<>();
	private List<Data> customData = new ArrayList<>();
	private FactoryPoolType factoryPoolChoseType = DEFAULT_FACTORYPOOL_TYPE;
	private int factoryPoolSize = DEFAULT_FACTORY_POOL_SIZE;

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

	public PsmmConfiguration setFactoryPoolChoseType(FactoryPoolType factoryPoolChoseType) {
		this.factoryPoolChoseType = factoryPoolChoseType;
		return this;
	}
}
