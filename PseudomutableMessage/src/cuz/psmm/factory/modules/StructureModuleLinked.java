package cuz.psmm.factory.modules;

import cuz.psmm.accessories.StateLess;
import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.data.Data;
import cuz.psmm.message.Message;

@StateLess
final class StructureModuleLinked extends Module {


	public StructureModuleLinked(Module creationModule) {
		super(creationModule, "Linked");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Message<T> createMessage(Message.Type type,Message<T> messageBeingWrapped,
			Data data) {
		// TODO Auto-generated method stub
		return this.collaberativeModule.createMessage(type,messageBeingWrapped, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// TODO Auto-generated method stub
		psmmFactory.setModule(this);
	}
}
