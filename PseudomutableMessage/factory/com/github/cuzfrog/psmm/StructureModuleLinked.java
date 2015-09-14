package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.Message;
import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.StateLess;

@StateLess
final class StructureModuleLinked extends Module {


	public StructureModuleLinked(Module creationModule) {
		super(creationModule, "Linked");
	}

	@Override
	public <T> Message<T> createMessage(Messages.Style type,Message<T> messageBeingWrapped,
			Data data) {
		return this.getCollaberativeModule().createMessage(type,messageBeingWrapped, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		psmmFactory.setModule(this);
	}
}
