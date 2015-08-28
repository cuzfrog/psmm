package cuz.my.psmm;

import cuz.my.psmm.PsmmFactory;
import cuz.my.psmm.accessories.NotThreadSafe;

/**
 * For hiding {@link PsmmFactory}, and to ensure you've invoked {@link #cook()}
 * with cascading method calls.
 * <p>
 * So do not grab a reference of it. Implementation {@link RawMessageImpl} of
 * this interface is light weight containing only two references, so that it's
 * been created every time when a new Message is created. It wraps a factory and
 * stores the last Message for new Message to wrap.
 * <p>
 * The factory wrapped is fetched from factory pool.
 * 
 * @author cuzfrog
 * @see TMessage
 *
 * 
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
	 * @see UMessage
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
