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
	public <K,T> Message<K,T> createMessage(Messages.Style type,Message<K,T> messageBeingWrapped,
			Data data) {
		return this.getCollaberativeModule().createMessage(type,messageBeingWrapped, data);
	}
}
