package com.github.cuzfrog.psmm;

/**
 * Typed message builder. 
 * 
 * @author Cause Chung
 * @see TMessage
 */
@NotThreadSafe
public interface TBuilder<K,V> {

	/**
	 * Return a TMessage with data previously set.
	 * 
	 * <p>
	 * This method invokes the inner factory's commit method to commit all the
	 * data previously set by TBuilder into a new
	 * TMessage, and return it. Every time you create or change a
	 * message, you must call this finally.
	 * 
	 * @return TMessage built message with data previously set.
	 * @see TMessage
	 * @see UMessage
	 * @see UBuilder
	 */
	public abstract TMessage<K,V> build();

	/**
	 * Prepare a key-value pair intended to store into the message, and return a reference
	 * to {@link TBuilder} which is itself for cascading calling.
	 * 
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param datum
	 *            value to be associated with the specified key
	 * @return RawMessage object which is itself.
	 */
	public abstract TBuilder<K,V> set(K key, V datum);

}
