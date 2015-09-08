package cuz.my.psmm;

final class CreationModuleUncached extends Module {

	public CreationModuleUncached() {
		super(null,"Uncached");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> MessageAdaptorInterface<T> createMessage(Messages.Style type,MessageAdaptorInterface<T> messageBeingWrapped, Data data) {
		// TODO Auto-generated method stub
		return PsmmSystem.getConcretMessage(type, messageBeingWrapped, data);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// Do nothing
	}

}
