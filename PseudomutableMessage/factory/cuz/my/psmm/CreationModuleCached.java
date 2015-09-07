package cuz.my.psmm;

import cuz.my.psmm.data.Data;

/**
 * Factory module for creating cached message.
 * <p>
 * When its createMessage method is called, it first checks if there is message
 * with the same signature in the message pool. If there's not, it creates one
 * and put it into the pool. However NO guarantee for above, because the process
 * of checking and putting a message into the pool is not thread-safe, though
 * the pool implements a {@code ConcurrentHashMap<Sigature,TMessage<T>>} and no
 * duplicate messages in the pool. Thus same messages(messages with equal
 * Signature) may have been created and put more than once. Fortunately, this
 * only happen when messages with same Signature are being asked by two or more
 * thread at the same time, and this possibility is low and once message-putting
 * has completed it can be checked thread-safely.
 * 
 * <p>
 * The signature used as key in message pool is actually an interface named
 * {@link Checkable} to abstract message and data together. Therefore, without
 * creating new message instance, it uses Data as signature to find a match
 * message.
 * 
 * @author Cause Chung
 *
 */
final class CreationModuleCached extends Module {

	public CreationModuleCached() {
		super(null, "Cached");
		// CreationModule is at the bottom of the chain.
	}

	@Override
	public <T> Message<T> createMessage(Messages.Style type,
			Message<T> messageBeingWrapped, Data data) {

		Data fatherData = messageBeingWrapped.readData();
		Data signature;
		if (fatherData != null) {
			signature = messageBeingWrapped.readData().merge(data);
		} else {
			signature = data;
		}

		Message<T> message;
		if ((message = PsmmSystem.seekMessage(signature)) == null) {
			message = PsmmSystem.getConcretMessage(type, messageBeingWrapped,
					data, signature);
		}

		return message;
	}
}
