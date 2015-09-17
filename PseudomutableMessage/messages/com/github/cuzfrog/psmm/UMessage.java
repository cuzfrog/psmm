package com.github.cuzfrog.psmm;

import java.util.Map;

import com.github.cuzfrog.psmm.MessageCommonInterface;
import com.github.cuzfrog.psmm.exceptions.PsmmCannotRegressExeption;

/**
 * An untyped version for {@code TypedMessage}.
 * 
 * <p>
 * This is the interface for psmm(PSeudoMutableMessage). It provides
 * mutable-like behavior, but remain immutable by wrapping a psmm or completely
 * create a new data structure.
 * <p>
 * This interface only receive primitives' boxes and String value. When returning a
 * value, you need to explicitly do downcast. A good practice is to use part of
 * the key to infer its value's type.(Since method get return Object, value that 
 * has been set will be boxed anyway, so method set doesn't take primitives.)
 * 
 * @author Cause Chung
 * @see TMessage
 */
public interface UMessage extends MessageCommonInterface, UntypedMessageSetInterface {
	/**
	 * Return a single value by a specified key. If the value cannot be found by
	 * the key, it'll return null.
	 * 
	 * @param key key with which the specified value is associated
	 * @return Object value associated with the specified key
	 */
	public abstract Object get(String key);

	/**
	 * Change into a new raw message without setting data.
	 * 
	 * <P>
	 * Actually return a raw message that wraps this message.This method is
	 * unnecessary for most cases, you can directly call set when invoking with
	 * cascading style.
	 * 
	 * @return Raw message without setting data.
	 */
	public abstract UntypedRawMessage raw();

	/**
	 * Return message's whole data as a Map. The Map is newly created and not
	 * involved in the data structure in the message. As long as each element of
	 * the data is immutable (and this is assumed principle), you can do
	 * whatever to the returned Map.
	 * <p>
	 * If the message doesn't contain any value, this will return an empty Map.
	 * 
	 * @return data stored in this message as a {@code Map<String, Object>}.
	 */
	public abstract Map<String, ? extends Object> getAll();
	
	/**
	 * Try to regain last message before last cook.
	 * <p>
	 * As for linked message with depth more than one, there's always another
	 * message inside it. So it's able to provide a simulation of
	 * "time regression" method to get that message. This method actually return
	 * the encapsulated message.
	 * 
	 * 
	 * @return last message before last cook.
	 * @throws PsmmCannotRegressExeption
	 *             when depth is one.
	 */
	public abstract UMessage regress() throws PsmmCannotRegressExeption;
}
