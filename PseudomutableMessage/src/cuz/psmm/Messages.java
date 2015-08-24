package cuz.psmm;

public final class Messages {
	private static FactoryPool factoryPool=new FactoryPool(null);
	private Messages(){}
	/**
	 * 
	 * @param type
	 * @param c
	 * @return PsmmFactory as RawMessage.
	 */
	@SuppressWarnings("unchecked")
	public static <T> RawMessage<T> create(Type type, Class<T> c) {
		return factoryPool.seekFactory(type, RootMessage.getInstance());
	}

	static <T> RawMessage<T> fetch(Type type, Message<T> messageBeingWrapped){
		return factoryPool.seekFactory(type, messageBeingWrapped);
	}
	
	static void release(Integer position){
		
	}
	public static enum Type {
		LINKED_MAP, FLAT_MAP, LINKED_SINGLE, FLAT_SINGLE
	}
}
