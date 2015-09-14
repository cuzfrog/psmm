package com.github.cuzfrog.psmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.cuzfrog.psmm.Module;
import com.github.cuzfrog.psmm.NotThreadSafe;

/**
 * Class for configuring psmm when initiating.
 * <p>
 * Constructor or set-method both do configuration work. PsmmSystem will create
 * a default PsmmConfiguration if you don't specify one.
 * <p>
 * Default factory pool size is 16, this should be set more than the threads ID
 * there'll be. Default factory pool type is MAP, which implements a
 * ConcurrentHashMap. Default message linked max depth is 256, which means
 * there's no limitation on depth. Note that long message chain could occupy
 * large part of memory.
 * 
 * @author Cause Chung
 *
 */
@NotThreadSafe
public final class PsmmConfiguration {

	public enum FactoryPoolType {
		MAP, NULL
	}

	// constants:
	private static final FactoryPoolType DEFAULT_FACTORYPOOL_TYPE = FactoryPoolType.MAP;
	private static final int DEFAULT_FACTORY_CHECK_INTERVAL= 5*60; //5 minutes
	private static final int DEFAULT_FACTORY_POOL_SIZE = 16;
	private static final int DEFAULT_MESSAGE_MAX_DEPTH = 256;

	// configuration members:
	private Map<String, Module> customModules = new HashMap<>();
	private List<Data> customData = new ArrayList<>();
	private FactoryPoolType factoryPoolChoseType = DEFAULT_FACTORYPOOL_TYPE;
	private int factoryPoolSize = DEFAULT_FACTORY_POOL_SIZE;
	private int factoryPoolCheckInterval=DEFAULT_FACTORY_CHECK_INTERVAL;
	private int messageMaxDepth=DEFAULT_MESSAGE_MAX_DEPTH;

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

	public int getMessageMaxDepth() {
		return messageMaxDepth;
	}

	public void setMessageMaxDepth(int messageMaxDepth) {
		this.messageMaxDepth = messageMaxDepth;
	}

	public int getFactoryPoolCheckInterval() {
		return factoryPoolCheckInterval;
	}

	public void setFactoryPoolCheckInterval(int factoryPoolCheckInterval) {
		this.factoryPoolCheckInterval = factoryPoolCheckInterval;
	}
}
