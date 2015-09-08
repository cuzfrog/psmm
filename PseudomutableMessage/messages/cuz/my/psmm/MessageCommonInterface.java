package cuz.my.psmm;

import java.io.Serializable;

/**
 * Common method interface.
 * @author Cause Chung
 *
 */
interface MessageCommonInterface extends Serializable {
	/**
	 * Psmm are not supporting signature right now.
	 * <p>
	 * Signature is calculated by the fields data and type of a message.
	 * Messages that have the same type and values, i.e. you can get equal
	 * values by the same key, have same signature.
	 * 
	 * @return signature of this message.
	 */
	 @Deprecated byte[] getSignature();
	
	/**
	 * Return the number of messages this one has wrapped.
	 * <p>
	 * see {@link TMessage} for details of message structure. Flat message
	 * always has the depth equal one.
	 * 
	 * @return position of the message in the linked structure.
	 */
	int depth();
	
	
}
