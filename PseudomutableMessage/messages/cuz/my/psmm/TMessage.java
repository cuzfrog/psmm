package cuz.my.psmm;

import java.util.Map;

/**
 * A typed message interface.
 * 
 * <p>
 * This is the interface for psmm(PSeudoMutableMessage). It provides
 * mutable-like behavior, but remain immutable by wrapping a psmm or completely
 * create a new data structure.
 * 
 * <p>
 * There are several final implementations for different data structures and
 * performances in given situations. However, you can only create them via
 * static method {@link Messages#create}.<br>
 * Message type is used for instructing how to assemble the factory with a
 * specified message-generating behavior. Type of a message is stored inside a
 * message as an enum {@link Messages.Style} , so that it can get a factory of
 * the same type without telling it the type.
 * 
 * <p>
 * When you want to store variant types of data, use {@code Object} as
 * parameter,but be ware of the mutability.To make sure data stored is
 * immutable, use {@link Object} which only accepts immutable basic types such
 * as {@code String}, {@code Integer}.If you only send one value per message,
 * use {@link SMessage} which take that value directly as inner datum instead of
 * data structure.
 * 
 * @author cuzfrog
 *
 * @param <T>
 *            The type of data this message carries. <br>
 *            There's no check for T's mutability. You have to ensure T is
 *            immutable.<br>
 * 
 * 
 *
 * @see Object
 */
@ThreadSafe
public interface TMessage<T> extends MessageCommonInterface {

	/**
	 * Return a single value by a specified key. If the value cannot be found by
	 * the key, it'll return null.
	 * 
	 * @param key
	 * @return T value associated with key.
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
	 * to {@link TypedRawMessage} for cascading calling. This simulates the fact
	 * that if the message is modified, it'll turn into a raw message.
	 * 
	 * <p>
	 * The method in Message actually does nothing. It first gives its own
	 * reference to a newly created RawMessage which invokes the static method
	 * {@link PsmmSystem#seekFactory(Messages.Style)} to get a
	 * {@link PsmmFactory} reference , which is enveloped in the RawMessage, as
	 * this only presents to you as a RawMessage. Then it invokes the factory's
	 * homonymous method {@link PsmmFactory#set(String, Object)} to store datum
	 * into the factory object.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param datum
	 *            value to be associated with the specified key
	 * @return outer RawMessage object by which this message is wrapped.
	 */
	public abstract TypedRawMessage<T> set(String key, T datum);

	/**
	 * Change into a new raw message without setting data.
	 * 
	 * <P>
	 * Actually return a raw message that wraps this message.This method is
	 * unnecessary for most cases, you can directly call set when cascadingly
	 * invoking.
	 * 
	 * @return Raw message without setting data.
	 */
	public abstract TypedRawMessage<T> raw();
	
	/**
	 * Try to get last message before last cook.
	 * <p>
	 * As for linked message with depth more than one, there's always another message inside it.
	 * So it's able to provide a simulation of "time regression" method to get that message.
	 * If depth is one, it'll return singleton instance of {@link RootMessage}
	 * 
	 * @return last message before last cook.
	 * @throws PsmmUnsupportedOperationException when none-linked message calls this method.
	 */
    public abstract TMessage<T> regress();
}
