package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.exceptions.PsmmCannotRegressExeption;

/**
 * An mediate interface between low level class( {@link AbstractMessage},
 * {@link RootMessage}) and high level ones({@link TMessage},
 * {@link UMessage})
 * 
 * @author Cause Chung
 *
 * @param <T>
 */
interface Message<K,T> extends TMessage<K,T>, UMessage {
	
	Message<K,T> regress() throws PsmmCannotRegressExeption;
	
	AbstractBuilder<K,T> builder();
	
	/**
	 * Read all data in a message. And return one Data object.
	 * @return all data the message contains, including those stored in its parents
	 */
	Data readData();
	
}
