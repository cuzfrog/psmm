package cuz.psmm;

import cuz.psmm.factoryModules.DataStructure;
import cuz.psmm.factoryModules.Module;

final class GeneralPsmmFactory implements PsmmFactory {

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

		Message<T> newMessage = module.createMessage(messageBeingWrapped, data);
		//once a new message has been created, delete this.data reference for safety.
		data = null;
		return newMessage;

	}

	@Override
	public <T> void set(String key, T datum) {
		// TODO Auto-generated method stub
		data.set(key, datum);
	}

}
