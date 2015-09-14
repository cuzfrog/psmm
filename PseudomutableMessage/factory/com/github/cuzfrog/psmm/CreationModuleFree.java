package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.Message;
import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.PsmmSystem;

final class CreationModuleFree extends Module {

	public CreationModuleFree() {
		super(null,"Free");
	}

	@Override
	public <T> Message<T> createMessage(Messages.Style type,Message<T> messageBeingWrapped, Data data) {
		return PsmmSystem.getConcretMessage(type, messageBeingWrapped, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// Do nothing
	}

}
