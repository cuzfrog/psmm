package cuz.my.psmm;


public interface UntypedRawMessage extends UntypedMessageSetInterface {
	/**
	 * Return a cooked {@code UntypedMessage} with the data previously set.
	 * 
	 * <p>
	 * This method invokes the inner factory's commit method to commit all the
	 * data previously set by {@code UntypedRawMessage} into a new
	 * {@code TypedMessage}, and return it. Every time you create or change a
	 * message, you must call this.
	 * 
	 * @return a cooked {@code UntypedMessage}.
	 * @see PsmmFactory
	 * @see TMessage
	 * @see UMessage
	 * @see TypedRawMessage
	 */
	UMessage cook();
}
