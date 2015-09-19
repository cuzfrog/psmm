package com.github.cuzfrog.psmm;

/**
 * Interface that contains set methods for untyped message.
 * @author Cause Chung
 *
 */
interface UntypedMessageSetInterface {
	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Integer value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Short value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Long value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Boolean value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Float value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Double value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Character value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, Byte value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UBuilder set(String key, String value);
}
