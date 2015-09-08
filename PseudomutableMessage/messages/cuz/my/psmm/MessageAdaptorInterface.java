package cuz.my.psmm;

import cuz.my.psmm.exceptions.PsmmCannotRegressExeption;

/**
 * An mediate interface between low level class( {@link AbstractMessage},
 * {@link RootMessage}) and high level ones({@link TMessage},
 * {@link UMessage})
 * 
 * @author Cause Chung
 *
 * @param <T>
 */
interface MessageAdaptorInterface<T> extends TMessage<T>, UMessage {
	AbstractRawMessage<T> raw();
	
	MessageAdaptorInterface<T> regress() throws PsmmCannotRegressExeption;
	
	/**
	 * Read all data in a message. And return one Data object.
	 * @return all data the message contains, including those stored in its parents
	 */
	Data readData();
}