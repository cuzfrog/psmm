package cuz.psmm.factoryModules;

import cuz.psmm.Message;
import cuz.psmm.Messages;
import cuz.psmm.accessoaries.StateLess;
import cuz.psmm.messages.CommonMessage;

@StateLess
class LinkedMapModule implements Module {

	@Override
	public <T> Message<T> createMessage(Message<T> messageBeingWrapped,
			DataStructure data) {
		// TODO Auto-generated method stub
		return new CommonMessage<>(Messages.Type.LINKED_MAP, messageBeingWrapped,  data);
	}


	@Override
	public  DataStructure designateData() {
		// TODO Auto-generated method stub
		return new DataStructureMap();
	}
}
