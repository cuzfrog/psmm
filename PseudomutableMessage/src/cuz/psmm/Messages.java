package cuz.psmm;


public final class Messages {
	/**
	 * 
	 * @param type
	 * @param c
	 * @return PsmmFactory as RawMessage.
	 */
	@SuppressWarnings("unchecked")
	public static <T> RawMessage<T> create(Type type, Class<T> c) {
		return PsmmFactory.seekFactory(type, RootMessage.getInstance());
	}

	public static enum Type {
		LINKED_MAP, FLAT_MAP, LINKED_SINGLE, FLAT_SINGLE
	}
}
