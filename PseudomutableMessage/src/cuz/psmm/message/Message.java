package cuz.psmm.message;

import java.util.Map;

import cuz.psmm.Psmm;
import cuz.psmm.accessories.ThreadSafe;
import cuz.psmm.factory.PsmmFactory;

/**
 * This is the interface for psmm(PSeudoMutableMessage). It provides
 * mutable-like behavior, but remain immutable by wrapping a psmm or completely
 * create a new data structure.
 * 
 * <p>
 * There are several final implementations for different data structures and
 * performances in given situations: <br>
 * {@link cuz.psmm.message.UncachedMessage} <br>
 * However, you can only create them via static method {@link #create}. <br>
 * Type of a message is stored inside a message as an enum {@link Message.Type}
 * , in order to get factory of the same type conveniently.
 * 
 * <p>
 * When you want to store variant types of data, use {@code Object} as
 * parameter,but be ware of the mutability.To make sure data stored is
 * immutable, use {@link UntypedMessage} which only accepts immutable basic
 * types such as {@code String}, {@code Integer}.If you only send one value per
 * message, use {@link SimpleMessage} which take that value directly as inner
 * datum instead of data structure.
 * 
 * @author cuzfrog
 *
 * @param <T>
 *            The type of data this message carries. <br>
 *            There's no check for T's mutability. You have to ensure T is
 *            immutable.<br>
 * 
 * 
 */
@ThreadSafe
public abstract class Message<T> implements Psmm {

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
	 * Return a single value by a specified key. If the value cannot be found by
	 * the key, it'll return null.
	 * 
	 * @param key
	 * @return T value set by key.
	 */
	public abstract T get(String key);

	/**
	 * Return message's whole data as a Map. The Map is newly created and not
	 * involved in the data structure in the message. As long as each element of
	 * the data is immutable (and this is assumed principle), you can do
	 * whatever to the returned Map.
	 * <p>
	 * If the message doesn't contain any value, this will return an empty Map.
	 * 
	 * @return data stored in this message as a {@code Map<String, T>}.
	 */
	public abstract Map<String, T> getAll();

	/**
	 * Set a value into the message by an associated key, and return a reference
	 * to {@link RawMessage} for cascading calling. This simulates that if the
	 * message is modified, it'll turn into a raw message.
	 * 
	 * <p>
	 * The method in Message actually does nothing. It first invokes the static
	 * method {@link PsmmFactory#seekFactory(Type)} to bind a
	 * {@link PsmmFactory} to the original message (wrap the message), which is
	 * the same object of RawMessage, but presents to you as a RawMessage. Then
	 * it invokes the homonymous method {@link PsmmFactory#set(String, Object)}
	 * to store datum into the factory object.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param datum
	 *            value to be associated with the specified key
	 * @return outer PsmmFactory object by which this message is wrapped.
	 */
	public abstract RawMessage<T> set(String key, T datum);

	/**
	 * Return a signature of this message.<br>
	 * 
	 * 
	 * 
	 * Signature is calculated by the fields data and type of a message.
	 * Messages that have the same type and values, i.e. you can get equal
	 * values by the same key, have same signature.
	 * 
	 * @return signature of this message.
	 */
	public abstract byte[] getSignature();

	/**
	 * Return wrapped depth of the message.<br>
	 * see {@link cuz.psmm.message.UncachedMessage} for details of message
	 * structure.
	 * 
	 * @return position of the message in the linked structure.
	 */
	public abstract Integer depth();

	/**
	 * 
	 * @param type
	 * @param c
	 * @return PsmmFactory as RawMessage.
	 */
	public static <T> RawMessage<T> create(Message.Type type, Class<T> c) {

		Message<T> rootMessage = RootMessage.getInstance();
		return new RawMessageImpl<>(PsmmFactory.seekFactory(type), rootMessage);
	}

}
