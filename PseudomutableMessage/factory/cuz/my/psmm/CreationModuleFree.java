package cuz.my.psmm;

final class CreationModuleFree extends Module {

	public CreationModuleFree() {
		super(null,"Free");
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
