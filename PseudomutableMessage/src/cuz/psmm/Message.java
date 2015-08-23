package cuz.psmm;

import java.util.Map;

public interface  Message<T> {

	
	T get(String key);
	T get();
	Map<String,T> getAll();
	byte[] getSignature();
	Integer depth();
	RawMessage<T> set(String key,T datum);
	RawMessage<T> set(T datum);
	
	@SuppressWarnings("unchecked")
	public static <T> RawMessage<T> create(Type type,Class<T> c){
		return PsmmFactory.seekFactory(type,RootMessage.getInstance());
	}
	
	static enum Type{
		LINKED
	}

	
}
