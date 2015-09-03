package cuz.my.psmm;

import cuz.my.psmm.MessageCommonInterface;

/**
 * An untyped version for {@code TypedMessage}.
 * 
 * <p>
 * This is the interface for psmm(PSeudoMutableMessage). It provides
 * mutable-like behavior, but remain immutable by wrapping a psmm or completely
 * create a new data structure.
 * <p>
 * This interface only receive primitives and String value. When returning a
 * value, you need to explicitly do downcast. A good practice is to use part of
 * the key to infer its value's type.
 * 
 * @author cuzfrog
 * @see TMessage
 */
public interface UMessage extends MessageCommonInterface, UntypedMessageSetInterface {
	/**
	 * Return a single value by a specified key. If the value cannot be found by
	 * the key, it'll return null.
	 * 
	 * @param key
	 * @return Object value associated with key.
	 */
	public abstract Object get(String key);

	/**
	 * Change into a new raw message without setting data.
	 * 
	 * <P>
	 * Actually return a raw message that wraps this message.This method is
	 * unnecessary for most cases, you can directly call set when cascadingly
	 * invoking.
	 * 
	 * @return Raw message without setting data.
	 */
	public abstract UntypedRawMessage raw();
}
