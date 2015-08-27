package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class GeneralPsmmFactory extends PsmmFactory {

	private Data data;
	private Module module;
	private Messages.Type type;

	@Override
	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public <T> TypedMessage<T> commit(TypedMessage<T> messageBeingWrapped) {
		// TODO Auto-generated method stub

		TypedMessage<T> newMessage = module.createMessage(type, messageBeingWrapped, data);

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
	public <T> PsmmFactory assemble(Module module, Messages.Type type) {
		this.type = type;
		module.setup(this);
		return this;
	}

}
