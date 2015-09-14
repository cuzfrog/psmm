package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.AbstractRawMessage;
import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.Message;
import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.NotThreadSafe;
import com.github.cuzfrog.psmm.RawMessageImpl;

//assume Factory is run by single thread.
@NotThreadSafe
final class PsmmFactoryImpl implements PsmmFactory {

	private Data data;
	private Module module;
	private Messages.Style type;
	/**
	 * Raw message binded with this factory.
	 */
	private final AbstractRawMessage<?> rawMessageRefr=new RawMessageImpl<>(this);

	@Override
	public <T> Message<T> commit(Message<T> messageBeingWrapped) {
		// TODO Auto-generated method stub

		Message<T> newMessage = module.createMessage(type, messageBeingWrapped, data);

		// once a new message has been created, delete this.data reference
		// for safety.
		data = null;

		return newMessage;

	}

	@Override
	public <T> void set(String key, T datum) {
		// TODO Auto-generated method stub
		data.set(key, datum);
	}

	@Override
	public void assemble(Module module, Messages.Style type) {
		this.type = type;
		module.setup(this);
	}

	// module functions:
	@Override
	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public void setModule(Module module) {
		this.module = module;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> AbstractRawMessage<T> getRawMessage() {
		// TODO Auto-generated method stub
		return (AbstractRawMessage<T>) rawMessageRefr;
	}
}
