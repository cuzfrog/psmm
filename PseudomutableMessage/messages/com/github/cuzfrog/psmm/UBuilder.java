package com.github.cuzfrog.psmm;

/**
 * An untyped version of {@link TBuilder}.
 * 
 * @author Cause Chung
 * @see UMessage
 */
@NotThreadSafe
public interface UBuilder extends UntypedMessageSetInterface {
	/**
	 * Return an {@code UMessage} with the data previously set.
	 * 
	 * 
	 * @return UMessage.
	 * @see UMessage
	 * @see TMessage
	 * @see TBuilder
	 */
	UMessage build();
}
