package cuz.my.psmm;

import java.util.Map;

import cuz.my.psmm.data.Data;

@StateLess
final class StructureModuleFlat extends Module {


	public StructureModuleFlat(Module creationModule) {
		super(creationModule,"Flat");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Message<T> createMessage(Messages.Style type,Message<T> messageBeingWrapped,
			Data data) {
		Map<String, T> parentData = messageBeingWrapped.getAll();
		Map<String, T> newData = data.getAll();
		if (parentData != null)
			parentData.putAll(newData);
		Message<T> rootMessage =  PsmmSystem.getRootMessage();
		return this.getCollaberativeModule().createMessage(type,rootMessage, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// TODO Auto-generated method stub
		psmmFactory.setModule(this);
	}

}
