package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.Message;
import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.StateLess;

@StateLess
final class StructureModuleFlat extends Module {

	public StructureModuleFlat(Module creationModule) {
		super(creationModule, "Flat");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <K,T> Message<K,T> createMessage(Messages.Style type, Message<K,T> messageBeingWrapped, Data data) {
		Data parentData = messageBeingWrapped.readData();
		Data newDataToPutInto;
		if (parentData != null) {
			newDataToPutInto=parentData.merge(data);
		}else{
			newDataToPutInto=data;
		}
		Message<K,T> rootMessage = PsmmSystem.getRootMessage();
		return this.getCollaberativeModule().createMessage(type, rootMessage, newDataToPutInto);
	}
}
