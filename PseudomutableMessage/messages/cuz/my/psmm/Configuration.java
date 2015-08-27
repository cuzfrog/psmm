package cuz.my.psmm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuz.my.psmm.data.Data;

public class Configuration {

	public static enum FactoryPoolType {
		THREAD
	}

	private static final Integer DEFAULT_MESSAGE_POOL_SIZE = 20480;
	private static final FactoryPoolType DEFAULT_FACTORYPOOL_TYPE = FactoryPoolType.THREAD;

	private Integer messagePoolSize;
	private Map<String,Module> customModules = new HashMap<>();
	private List<Data> customData = new ArrayList<>();
	private FactoryPoolType factoryPoolChoseType;

	public Configuration() {
		this(DEFAULT_MESSAGE_POOL_SIZE);
	}

	public Configuration(Integer messagePoolSize) {
		this(messagePoolSize, DEFAULT_FACTORYPOOL_TYPE);
	}

	public Configuration(FactoryPoolType factoryPoolChoseType) {
		this(DEFAULT_MESSAGE_POOL_SIZE, factoryPoolChoseType);
	}

	public Configuration(Integer messagePoolSize, FactoryPoolType factoryPoolChoseType) {
		this.messagePoolSize = messagePoolSize;
		switch (factoryPoolChoseType) {
		case THREAD:
			this.factoryPoolChoseType = factoryPoolChoseType;
			break;
		default:
			break;
		}

	}

	public void addModule(Module module){
		customModules.put(module.getName(),module);
	}
	
	public void addData(Data data){
		customData.add(data);
	}
	public Integer getMessagePoolSize() {
		return messagePoolSize;
	}

	public Map<String,Module> getCustomModules() {
		return customModules;
	}

	public List<Data> getCustomData() {
		return customData;
	}

	public FactoryPoolType getFactoryPoolChoseType() {
		return factoryPoolChoseType;
	}

}
