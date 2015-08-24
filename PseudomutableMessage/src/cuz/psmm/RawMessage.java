package cuz.psmm;

/**
 * This is an alias interface for {@link PsmmFactory}, only to ensure you've
 * invoked {@link #cook()} with cascading method calls. So do not grab a
 * reference of it.
 * 
 * @author cuzfrog
 * @see Message
 */
public interface RawMessage<T>  {

	

	Message<T> cook();
	RawMessage<T> set(String key,T datum);
}
