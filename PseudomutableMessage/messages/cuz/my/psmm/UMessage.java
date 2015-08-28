package cuz.my.psmm;

import cuz.my.psmm.Psmm;

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
public interface UMessage extends Psmm,UntypedMessageSetInterface {
	/**
	 * Return a single value by a specified key. If the value cannot be found by
	 * the key, it'll return null.
	 * 
	 * @param key
	 * @return Object value associated with key.
	 */
	public abstract Object get(String key);

	
	/**
	 * Return a signature of this message.<br>
	 * 
	 * 
	 * 
	 * Signature is calculated by the fields data and type of a message.
	 * Messages that have the same type and values, i.e. you can get equal
	 * values by the same key, have same signature.
	 * 
	 * @return signature of this message.
	 */
	public abstract byte[] getSignature();
}
