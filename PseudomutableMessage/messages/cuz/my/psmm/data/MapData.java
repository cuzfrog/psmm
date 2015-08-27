package cuz.my.psmm.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import cuz.my.psmm.TypedMessage;

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
	public byte[] getSignature() throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		for(String key:data.keySet()){
			Object value=data.get(key);
			if(value instanceof String ){
				outputStream.write(((String)value).getBytes());
			}else if(value instanceof TypedMessage){
				outputStream.write(((TypedMessage<?>)value).getSignature());
			}else{
				ObjectOutputStream os = new ObjectOutputStream(outputStream);
				os.writeObject(value);
			}
		}
		
		return outputStream.toByteArray();
	}

	@Override
	public void set(Object datum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return null;
	}


}
