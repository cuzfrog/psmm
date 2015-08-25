package cuz.psmm.factoryModules;

import java.util.HashMap;
import java.util.Map;

class DataStructureMap implements DataStructure {

	private Map<String,Object> data=new HashMap<>();

	@Override
	public <T> void set(String key, T datum) {
		// TODO Auto-generated method stub
		data.put(key,  datum);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		// TODO Auto-generated method stub
		return (T) data.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Map<String, T> getAll() {
		// TODO Auto-generated method stub
		return (Map<String, T>) data;
	}
	
}
