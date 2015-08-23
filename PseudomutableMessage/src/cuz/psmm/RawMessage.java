package cuz.psmm;

/**
 * This is an alias interface for {@link PsmmFactory}, only to ensure you've
 * invoked {@link #done()} with cascading method calls. So do not grab a
 * reference of it.
 * 
 * @author cuzfrog
 * @see {@link Message}
 */
public interface RawMessage<T>  {

	

	Message<T> done();
	RawMessage<T> set(String key,T datum);
	RawMessage<T> set(T datum);
}
