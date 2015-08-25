package cuz.psmm.factoryModules;

import java.util.Map;

public interface DataStructure {
	<T> void set(String key, T datum);

	<T> T get(String key);

	<T> Map<String, T> getAll();
}
