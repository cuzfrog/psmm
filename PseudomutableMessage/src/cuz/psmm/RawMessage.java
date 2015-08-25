package cuz.psmm;

import cuz.psmm.accessoaries.NotThreadSafe;

/**
 * This is an interface for hiding {@link PsmmFactory}, and to ensure you've
 * invoked {@link #cook()} with cascading method calls. So do not grab a
 * reference of it. <p>
 * Implementation {@link RawMessageImpl} of this interface is light weight containing only two
 * references, so that it's been created every time when a new Message is created.
 * It wraps a factory and stores the last Message for new Message to wrap.
 * <p>
 * The factory wrapped is fetched from {@link FactoryPool}, where you can see more detail.
 * @author cuzfrog
 * @see Message
 *
 * 
 */
@NotThreadSafe
public interface RawMessage<T> {

	Message<T> cook();

	RawMessage<T> set(String key, T datum);
}
