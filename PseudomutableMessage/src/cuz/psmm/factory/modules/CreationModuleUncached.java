package cuz.psmm.factory.modules;

import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.data.Data;
import cuz.psmm.message.Message;
import cuz.psmm.message.MessageHelper;

public class CreationModuleUncached extends Module {

	public CreationModuleUncached() {
		super(null,"Uncached");
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Message<T> createMessage(Message.Type type,Message<T> messageBeingWrapped, Data data) {
		// TODO Auto-generated method stub
		return MessageHelper.getConcretMessage(type, messageBeingWrapped, data, null);
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// Do nothing
	}

}
