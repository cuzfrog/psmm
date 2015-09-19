package com.github.cuzfrog.psmm;

import java.util.Map;

import com.github.cuzfrog.psmm.exceptions.PsmmCannotRegressExeption;

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
 * static method {@link Messages#createTyped()}.<br>
 * Message Style is used for instructing how to assemble the factory with a
 * specified message-generating behavior.
 * 
 * <p>
 * When you want to store variant types of data, use {@code Object} as type
 * parameter,but be ware of the mutability.To make sure data stored is
 * immutable, use {@link UMessage} which only accepts immutable basic types such
 * as {@code String}, {@code Integer}.If you only send one value per message,
 * use {@link SMessage}(not ready in version 1.0) which take that value directly
 * as inner datum instead of data structure.
 * 
 * @author Cause Chung
 *
 * @param <K>
 *            The type of key.
 * @param <T>
 *            The type of data this message carries. <br>
 *            There's no check for T's mutability. You have to ensure T is
 *            immutable.<br>
 * 
 * @see UMessage
 */
@ThreadSafe
public interface TMessage<K, T> extends MessageCommonInterface {

	/**
	 * Return a single value by a specified key. If the value cannot be found by
	 * the key, it'll return null.
	 * 
	 * @param key
	 *            key with which the specified value is associated
	 * @return T value associated with the specified key
	 */
	public abstract T get(K key);

	/**
	 * Return message's whole data as a Map. The Map is newly created and not
	 * involved in the data structure in the message. As long as each element of
	 * the data is immutable (and this is assumed principle), you can do
	 * whatever to the returned Map.
	 * <p>
	 * If the message doesn't contain any value, this will return an empty Map.
	 * 
	 * @return data stored in this message as a {@code Map}.
	 */
	public abstract Map<K, T> getAll();

	/**
	 * Set a value into the message with an associated key, and return a reference
	 * to {@link TBuilder} for cascading calling. 
	 * 
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param datum
	 *            value to be associated with the specified key
	 * @return outer RawMessage object by which this message is wrapped.
	 */
	public abstract TBuilder<K,T> set(K key, T datum);

	/**
	 * Return the builder without setting data.
	 * 
	 * <P>
	 * Actually return a raw message that wraps this message.This method is
	 * unnecessary for most cases, you can directly call set when cascadingly
	 * invoking.
	 * 
	 * @return Raw message without setting data.
	 */
	public abstract TBuilder<K,T> builder();

	/**
	 * Try to regain last message before last cook.
	 * <p>
	 * As for linked message with depth more than one, there's always another
	 * message inside it. So it's able to provide a simulation of
	 * "time regression" method to get that message. If depth is one, it'll
	 * return singleton instance of {@link RootMessage}
	 * 
	 * @return last message before last cook.
	 * @throws PsmmCannotRegressExeption
	 *             when depth is one.
	 */
	public abstract TMessage<K,T> regress() throws PsmmCannotRegressExeption;
}
