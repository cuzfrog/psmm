package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.PsmmFactory;

/**
 * An interface for hiding {@link PsmmFactory}, and to ensure you've invoked {@link #build()}
 * with cascading method calls.
 * <p>
 * So do not grab a reference of it. Implementation {@link BuilderImpl} of
 * this interface contains two references, of which are binded factory and
 * stored the last Message as new Message to wrap.
 * <p>
 * The factory wrapped is fetched from factory pool.
 * 
 * @author Cause Chung
 * @see UMessage
 */
public interface UBuilder extends UntypedMessageSetInterface {
	/**
	 * Return a cooked {@code UntypedMessage} with the data previously set.
	 * 
	 * <p>
	 * This method invokes the inner factory's commit method to commit all the
	 * data previously set by {@code UBuilder} into a new
	 * {@code TypedMessage}, and return it. Every time you create or change a
	 * message, you must call this.
	 * 
	 * @return a cooked {@code UntypedMessage}.
	 * @see PsmmFactory
	 * @see TMessage
	 * @see Object
	 * @see TBuilder
	 */
	UMessage build();
}
