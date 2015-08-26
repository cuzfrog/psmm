;package cuz.psmm.factory.modules;

import cuz.psmm.accessories.StateLess;
import cuz.psmm.factory.datastructure.DataStructure;
import cuz.psmm.message.CommonMessage;
import cuz.psmm.message.Message;
import cuz.psmm.message.Message.Type;

@StateLess
final class LinkedMapModule extends Module {

	private static final Message.Type type=Message.Type.LINKED_MAP;
	
	@Override
	public <T> Message<T> createMessage(Message<T> messageBeingWrapped,
			DataStructure data) {
		// TODO Auto-generated method stub
		return new CommonMessage<>(type, messageBeingWrapped,  data);
	}


	@Override
	public  DataStructure designateData() {
		// TODO Auto-generated method stub
		return DataStructure.newDataStructure(DataStructure.Type.MAP);
	}


	@Override
	public Message.Type getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
