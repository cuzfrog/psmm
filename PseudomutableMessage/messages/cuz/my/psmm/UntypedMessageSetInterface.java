package cuz.my.psmm;

/**
 * Interface that contains set methods for untyped message.
 * @author cuzfrog
 *
 */
interface UntypedMessageSetInterface {
	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, int value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, short value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, long value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, boolean value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, float value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, double value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, char value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, byte value);

	/**
	 * Set value and return a raw message.
	 * 
	 * <p>
	 * see {@link TMessage#set(String, Object)} for details.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return outer Raw Message object by which this message is wrapped.
	 */
	public abstract UntypedRawMessage set(String key, String value);
}
