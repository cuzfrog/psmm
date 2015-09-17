package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Messages.Style;

//assume Factory is run by single thread.
@NotThreadSafe
final class PsmmFactoryImpl implements PsmmFactory {

	private Data data;
	private Module module;
	private Messages.Style type;
	/**
	 * Raw message binded with this factory.
	 */
	private final AbstractRawMessage<?> rawMessageRefr = new RawMessageImpl<>(this);

	@Override
	public <T> Message<T> commit(Message<T> messageBeingWrapped) {

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
	
	@Override
	public void assemble() {
		this.assemble(module,type);
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
	
	@Override
	public boolean isModuleReady(Style style) {
		return module==null?false:module.getStyle()==style;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public <T> AbstractRawMessage<T> getRawMessage() {
		return (AbstractRawMessage<T>) rawMessageRefr;
	}


}
