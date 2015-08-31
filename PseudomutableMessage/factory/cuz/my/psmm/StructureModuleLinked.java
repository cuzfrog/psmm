package cuz.my.psmm;

import cuz.my.psmm.data.Data;

@StateLess
final class StructureModuleLinked extends Module {


	public StructureModuleLinked(Module creationModule) {
		super(creationModule, "Linked");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> TMessage<T> createMessage(Messages.Style type,TMessage<T> messageBeingWrapped,
			Data data) {
		// TODO Auto-generated method stub
		return this.getCollaberativeModule().createMessage(type,messageBeingWrapped, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// TODO Auto-generated method stub
		psmmFactory.setModule(this);
	}
}
