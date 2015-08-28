package cuz.my.psmm.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class MapData extends Data {

	private Map<String, Object> data = new HashMap<>();

	@Override
	public <T> void set(String key, T datum) {
		// TODO Auto-generated method stub
		data.put(key, datum);
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
		return  (Map<String, T>) data;
	}
	
	@Override
	public byte[] getDataStream() throws IOException {	
		return Data.getDataStream(data);
	}


}
