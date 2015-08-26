package cuz.psmm.factory;

import cuz.psmm.exceptions.PsmmException;
import cuz.psmm.factory.datastructure.DataStructure;
import cuz.psmm.factory.modules.Module;
import cuz.psmm.message.Message;

final class GeneralPsmmFactory extends PsmmFactory {

	private DataStructure data;
	private Module module;

	@Override
	public <T> PsmmFactory assemble(Module module) {

		this.module = module;
		data = this.module.designateData();
		return this;
	}

	@Override
	public <T> Message<T> commit(Message<T> messageBeingWrapped) {
		// TODO Auto-generated method stub

		Message<T> newMessage = null;
		try {
			newMessage = module.createMessage(messageBeingWrapped, data);
		} catch (PsmmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
