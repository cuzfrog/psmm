package cuz.psmm.factoryModules;

import java.io.IOException;

public interface DataStructure {
	<T> void set(String key, T datum);

	<T> T get(String key);

	<T> T getAll();
	
	byte[] getSignature() throws IOException;
}
