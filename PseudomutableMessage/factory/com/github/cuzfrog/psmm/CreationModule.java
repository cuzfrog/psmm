package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.Message;
import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.PsmmSystem;

final class CreationModule extends Module {

	public CreationModule(String name) {
		super(null,name);
	}

	@Override
	public <T> Message<T> createMessage(Messages.Style type,Message<T> messageBeingWrapped, Data data) {
		return PsmmSystem.getConcretMessage(type, messageBeingWrapped, data);
	}
}
