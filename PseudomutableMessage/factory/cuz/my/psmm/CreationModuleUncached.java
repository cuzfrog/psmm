package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class CreationModuleUncached extends Module {

	public CreationModuleUncached() {
		super(null,"Uncached");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Message<T> createMessage(Messages.Style type,Message<T> messageBeingWrapped, Data data) {
		// TODO Auto-generated method stub
		return PsmmSystem.getConcretMessage(type, messageBeingWrapped, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// Do nothing
	}

}
