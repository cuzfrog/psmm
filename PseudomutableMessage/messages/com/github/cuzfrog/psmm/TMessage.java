package com.github.cuzfrog.psmm;

import java.util.Map;

/**
 * A typed message interface.
 * 
 * <p>
 * This is the interface for psmm. It provides mutable-like behavior, but remain
 * immutable by wrapping a psmm or completely create a new data structure.
 * 
 * <p>
 * There are several final implementations for different data structures and
 * performances in given situations. However, you can only create them via
 * static method {@link Messages#typedBuilder()}.<br>
 * Message Style is used for instructing how to assemble the factory with a
 * specified message-generating behavior.
 * 
 * <p>
 * When you want to store variant types of data, use {@code Object} as type
 * parameter,but be ware of the mutability. To make sure data stored is
 * immutable, use {@link UMessage} which only accepts immutable basic types such
 * as {@code String}, {@code Integer}.If you only send one value per message,
 * use {@link SMessage}(not ready in version 1.0) which takes that value
 * directly as inner datum instead of data structure.
 * 
 * @author Cause Chung
 *
 * @param <K>
 *            The type of key.
 * @param <T>
 *            The type of data this message carries. <br>
 *            There's no check for T's mutability. You have to ensure T is
 *            immutable.<br>
 */
@ThreadSafe
public interface TMessage<K, T> extends MessageCommonInterface {

	/**
	 * Return a single value by a specified key.
	 * 
	 * @param key
	 *            key with which the specified value is associated
	 * @return T value associated with the specified key, null if the key cannot
	 *         be found.
	 */
	public abstract T get(K key);

	/**
	 * Return message's whole data as a Map.
	 * <p>
	 * The Map is newly created and not involved in the data structure in the
	 * message. As long as each element of the data is immutable (and this is
	 * assumed principle), you can do whatever to the returned Map.
	 * 
	 * @return data stored in this message as a {@code Map}. If the message
	 *         doesn't contain any value, this will return an empty Map.
	 */
	public abstract Map<K, T> getAll();

	/**
	 * Prepare a key-value pair for a new message, and return a reference to
	 * {@link TBuilder}.
	 * 
	 * <p>
	 * It's allowed to directly invoke this method several times with cascading
	 * calling. But must call build to actually return a new message.
	 * 
	 * <p>
	 * NOTE: don't forget to save new message's reference to a new or old
	 * variant, or the new message immediately becomes garbage: <br>
	 * 
	 * <pre>
	 * {@code message.set(...).set(...).build() //ERROR: new message goes down the drain.}
	 * </pre>
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param datum
	 *            value to be associated with the specified key
	 * @return outer RawMessage object by which this message is wrapped.
	 */
	public abstract TBuilder<K, T> set(K key, T datum);

	/**
	 * Return the builder without setting data.
	 * 
	 * @return Raw message without setting data.
	 */
	public abstract TBuilder<K, T> builder();

	/**
	 * Try to regain last message before last build.
	 * <p>
	 * As for linked message with depth more than one, there's always another
	 * message inside it. So it's able to provide a simulation of
	 * "time regression" method to get that message.
	 * 
	 * @return last message before last build. Return <b>null</b> when {@code depth<=1},
	 *         i.e. there is no valid message inside .
	 */
	public abstract TMessage<K, T> regress();
}
