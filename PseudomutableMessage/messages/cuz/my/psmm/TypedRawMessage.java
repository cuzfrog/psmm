package cuz.my.psmm;

import cuz.my.psmm.PsmmFactory;

/**
 * An interface for hiding {@link PsmmFactory}, and to ensure you've invoked {@link #cook()}
 * with cascading method calls.
 * <p>
 * So do not grab a reference of it. Implementation {@link RawMessageImpl} of
 * this interface contains two references, of which are binded factory and
 * stored the last Message as new Message to wrap.
 * <p>
 * The factory wrapped is fetched from factory pool.
 * 
 * @author Cause Chung
 * @see TMessage
 */
@NotThreadSafe
public interface TypedRawMessage<T> {

	/**
	 * Return a cooked {@code TypedMessage} with the data previously set.
	 * 
	 * <p>
	 * This method invokes the inner factory's commit method to commit all the
	 * data previously set by {@code TypeRawMessage} into a new
	 * {@code TypedMessage}, and return it. Every time you create or change a
	 * message, you must call this.
	 * 
	 * @return a cooked {@code TypedMessage}.
	 * @see PsmmFactory
	 * @see TMessage
	 * @see Object
	 * @see UntypedRawMessage
	 */
	public abstract TMessage<T> cook();

	/**
	 * Set a value into the message by an associated key, and return a reference
	 * to {@link TypedRawMessage} which is itself for cascading calling.
	 * 
	 * <p>
	 * The method invokes its inner {@link PsmmFactory}'s homonymous method to
	 * set data.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param datum
	 *            value to be associated with the specified key
	 * @return RawMessage object which is itself.
	 */
	public abstract TypedRawMessage<T> set(String key, T datum);

}
