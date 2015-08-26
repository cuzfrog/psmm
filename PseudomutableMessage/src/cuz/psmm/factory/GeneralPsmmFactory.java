package cuz.psmm.factory;

import cuz.psmm.factory.data.Data;
import cuz.psmm.factory.modules.Module;
import cuz.psmm.message.Message;

final class GeneralPsmmFactory extends PsmmFactory {

	private Data data;
	private Module module;
	private Message.Type type;

	@Override
	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public void setModule(Module module) {
		this.module = module;
	}

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
	public <T> PsmmFactory assemble(Module module, Message.Type type) {
		this.type = type;
		module.setup(this);
		return this;
	}

}
