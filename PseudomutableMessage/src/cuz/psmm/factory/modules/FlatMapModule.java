package cuz.psmm.factory.modules;

import java.util.Map;

import cuz.psmm.accessories.StateLess;
import cuz.psmm.factory.datastructure.DataStructure;
import cuz.psmm.message.CommonMessage;
import cuz.psmm.message.Message;
import cuz.psmm.message.Message.Type;
import cuz.psmm.message.RootMessage;

@StateLess
final class FlatMapModule extends Module {

	private static final Message.Type type = Message.Type.FLAT_MAP;

	@Override
	public <T> Message<T> createMessage(Message<T> messageBeingWrapped,
			DataStructure data) {
		Map<String, T> parentData = messageBeingWrapped.getAll();
		Map<String, T> newData = data.getAll();
		if (parentData != null)
			newData.putAll(parentData);
		Message<T> rootMessage = RootMessage.getInstance();
		return new CommonMessage<>(type, rootMessage, data);
	}

	@Override
	public DataStructure designateData() {
		// TODO Auto-generated method stub
		return DataStructure.newDataStructure(DataStructure.Type.MAP);
	}

	@Override
	public Message.Type getType() {
		// TODO Auto-generated method stub
		return type;
	}

}
