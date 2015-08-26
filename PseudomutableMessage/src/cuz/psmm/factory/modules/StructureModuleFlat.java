package cuz.psmm.factory.modules;

import java.util.Map;

import cuz.psmm.accessories.StateLess;
import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.data.Data;
import cuz.psmm.message.Message;
import cuz.psmm.message.MessageHelper;

@StateLess
final class StructureModuleFlat extends Module {


	public StructureModuleFlat(Module creationModule) {
		super(creationModule,"Flat");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Message<T> createMessage(Message.Type type,Message<T> messageBeingWrapped,
			Data data) {
		Map<String, T> parentData = messageBeingWrapped.getAll();
		Map<String, T> newData = data.getAll();
		if (parentData != null)
			newData.putAll(parentData);
		Message<T> rootMessage = MessageHelper.getRootMessage();
		return this.collaberativeModule.createMessage(type,rootMessage, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// TODO Auto-generated method stub
		psmmFactory.setModule(this);
	}

}
