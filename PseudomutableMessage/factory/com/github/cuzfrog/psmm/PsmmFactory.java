package com.github.cuzfrog.psmm;

/**
 * Factory that behaves in the whole process of create message or "change" data.
 * <p>
 * There're three categories of class responsible to the behavior of Psmm. A
 * Message is immutable an object, which is what you send. A RawMessage is a
 * facade, to which you access when you set new data. And PsmmFactory is what
 * under the facade performing most works.
 * 
 * @author Cause Chung
 *
 */
@NotThreadSafe
interface PsmmFactory {

	/**
	 * Assemble modules in the factory, and specify a message type.
	 * 
	 * @param module
	 *            which is a chain of modules
	 * @param type
	 *            message type specified.
	 */
	public abstract void assemble(Module module, Messages.Style type);

	/**
	 * Help method to assemble the factory with its own module. Simply to recreate a new Data implementation.
	 */
	public abstract void assemble();
	
	/**
	 * Commit all the data previously set to generate a new message.
	 * <p>
	 * If message is cached, the message returned may not be new.
	 * 
	 * @param messageBeingWrapped
	 * @return new message.
	 */
	public abstract <K,T> Message<K,T> commit(Message<K,T> messageBeingWrapped);

	/**
	 * Set datum to the factory, prepare for a new message.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param datum
	 *            value to be associated with the specified key
	 */
	public abstract <K,T> void set(K key, T datum);

	/**
	 * Method for modules to access.
	 * 
	 * @param data
	 *            the Data designated.
	 */
	abstract void setData(Data data);

	/**
	 * Designate a module that has createMessage method to the factory.
	 * 
	 * @param module
	 *            module with creatMessage method.
	 */
	abstract void setModule(Module module);

	/**
	 * 
	 * @return true if the module's style matches the style demanded.
	 */
	abstract boolean isModuleReady(Messages.Style style);

	/**
	 * 
	 * @return the Raw message binded with this factory.
	 */
	abstract <K,T> AbstractBuilder<K,T> getRawMessage();
}
