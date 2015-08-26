package cuz.psmm.message;

import cuz.psmm.accessories.NotThreadSafe;
import cuz.psmm.factory.PsmmFactory;

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
 * @see Message
 *
 * 
 */
@NotThreadSafe
public interface RawMessage<T> {

	
	
	public abstract Message<T> cook();

	public abstract RawMessage<T> set(String key, T datum);
	
	
	
	
}
