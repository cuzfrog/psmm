package cuz.my.psmm;

import java.util.Map;

import cuz.my.psmm.accessories.StateLess;
import cuz.my.psmm.data.Data;

@StateLess
final class StructureModuleFlat extends Module {


	public StructureModuleFlat(Module creationModule) {
		super(creationModule,"Flat");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> TypedMessage<T> createMessage(Messages.Type type,TypedMessage<T> messageBeingWrapped,
			Data data) {
		Map<String, T> parentData = messageBeingWrapped.getAll();
		Map<String, T> newData = data.getAll();
		if (parentData != null)
			newData.putAll(parentData);
		TypedMessage<T> rootMessage = MessageHelper.getRootMessage();
		return this.getCollaberativeModule().createMessage(type,rootMessage, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// TODO Auto-generated method stub
		psmmFactory.setModule(this);
	}

}
