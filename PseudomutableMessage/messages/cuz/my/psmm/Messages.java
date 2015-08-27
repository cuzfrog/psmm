package cuz.my.psmm;

/**
 * An abstract class that has message creation methods and the definition of
 * message type.
 * <p>
 * There are several message interfaces：<br> {@link UntypedMessage} only accept
 * primitives and Strings, which ensures its data's immutability. However bad
 * {@link Data} implementation could violate it.<br>{@link TypedMessage} gives
 * flexibility to data carried, but you have to be very careful designing your
 * message, since TypedMessage doesn't check the mutability of the datum you set in.
 * 
 * @author cuzfrog
 *
 */
public abstract class Messages implements Psmm {

	/**
	 * Message types indicating the structures of messages.
	 * @author cuzfrog
	 *
	 */
	public static enum Type {
		LINKED_MAP {
			@Override
			public String toString() {
				return "UncachedLinkedMap";
			}
		},
		FLAT_MAP {
			@Override
			public String toString() {
				return "UncachedFlatMap";
			}
		},
		CACHED_LINKED_MAP {
			@Override
			public String toString() {
				return "CachedLinkedMap";
			}
		},
		CACHED_FLAT_MAP {
			@Override
			public String toString() {
				return "CachedFlatMap";
			}
		};

	}

	/**
	 * Create a new {@link UntypedRawMessage}.
	 * <p>
	 * There are several {@link Messages.Type}s of message you can choose for
	 * different usage.
	 * 
	 * @param messageType
	 *            instructing factory how to generate this message.
	 * @return UntypedRawMessage.
	 */
	public static UntypedRawMessage create(Messages.Type messageType) {
		TypedMessage<Object> rootMessage = RootMessage.getInstance();
		return new RawMessageImpl<>(PsmmFactory.seekFactory(messageType), rootMessage);
	}

	/**
	 * Create a new {@link UntypedRawMessage} .
	 * <p>
	 * This is a helper method with the default message type of
	 * {@link #LINKED_MAP}.
	 * 
	 * @return RawMessage.
	 */
	public static UntypedRawMessage create() {
		TypedMessage<Object> rootMessage = RootMessage.getInstance();
		return new RawMessageImpl<>(PsmmFactory.seekFactory(Type.LINKED_MAP), rootMessage);
	}

	/**
	 * Create a new {@link TypedRawMessage} with class parameter type specified.
	 * <p>
	 * There are several {@link Messages.Type}s of message you can choose for
	 * different usage.
	 * 
	 * 
	 * @param messageType
	 *            instructing factory how to generate this message.
	 * @param c
	 *            The class parameter type of this RawMessage.
	 * @return RawMessage.
	 */
	public static <T> TypedRawMessage<T> create(Messages.Type messageType, Class<T> c) {

		TypedMessage<T> rootMessage = RootMessage.getInstance();
		return new RawMessageImpl<>(PsmmFactory.seekFactory(messageType), rootMessage);
	}

	/**
	 * Create a new {@link TypedRawMessage} with class parameter type specified.
	 * <p>
	 * This is a helper method with the default message type of
	 * {@link #LINKED_MAP}.
	 * 
	 * @param c
	 *            The class parameter type of this RawMessage.
	 * @return RawMessage.
	 */
	public static <T> TypedRawMessage<T> create(Class<T> c) {

		TypedMessage<T> rootMessage = RootMessage.getInstance();
		return new RawMessageImpl<>(PsmmFactory.seekFactory(Type.LINKED_MAP), rootMessage);
	}
}
