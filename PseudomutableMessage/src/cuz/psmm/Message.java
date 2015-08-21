package cuz.psmm;


public interface Message extends SetInterface {

	Object get(String valuePath);
	Integer depth();
	
	public static RawMessage create(Type type){
		return PsmmFactory.seekFactory(type,RootMessage.getInstance());
	}
	
	static enum Type{
		COMMON
	}
}