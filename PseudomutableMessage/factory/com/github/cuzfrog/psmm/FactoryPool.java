package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.ThreadSafe;

/**
 * Pool for caching PsmmFactory and RawMessage.
 * <p>
 * When creating new messages, we need to use factory objects to encapsulate new
 * data for changing.This FactoryPool provides a mechanism to cache those
 * objects to reduce object creating overhead. For specific algorithm, see each
 * FactoryPool implementation.
 * 
 * @author Cause Chung
 * @see MapFactoryPool
 */
@ThreadSafe
interface FactoryPool {

	// Functionalities:
	PsmmFactory seekFactory(Messages.Style type);
	
	/**
	 * limit pool size
	 * @param sizeLimit pool's max size
	 * @return size of the pool before trim.
	 */
	int checkAndTrim(int sizeLimit);

}