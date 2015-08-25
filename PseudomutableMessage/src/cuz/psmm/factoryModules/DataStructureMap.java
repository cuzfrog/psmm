package cuz.psmm.factoryModules;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import cuz.psmm.Message;

class DataStructureMap implements DataStructure {

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
	public Map<String, Object> getAll() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public byte[] getSignature() throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		for(String key:data.keySet()){
			Object value=data.get(key);
			if(value instanceof String ){
				outputStream.write(((String)value).getBytes());
			}else if(value instanceof Message){
				outputStream.write(((Message<?>)value).getSignature());
			}else{
				ObjectOutputStream os = new ObjectOutputStream(outputStream);
				os.writeObject(value);
			}
		}
		
		return outputStream.toByteArray();
	}
}
