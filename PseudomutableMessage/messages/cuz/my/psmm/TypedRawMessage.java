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
 * @see TypedMessage
 *
 * 
 */
@NotThreadSafe
public interface TypedRawMessage<T> {

	
	/**
	 * 
	 * @return
	 */
	public abstract TypedMessage<T> cook();

	public abstract TypedRawMessage<T> set(String key, T datum);
	
	
	
	
}
