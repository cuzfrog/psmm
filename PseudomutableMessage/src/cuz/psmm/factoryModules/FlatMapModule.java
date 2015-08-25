package cuz.psmm.factoryModules;

import java.util.Map;

import cuz.psmm.Message;
import cuz.psmm.Messages;
import cuz.psmm.accessoaries.StateLess;
import cuz.psmm.messages.RootMessage;
import cuz.psmm.messages.CommonMessage;

@StateLess
class FlatMapModule implements Module {

	@Override
	public <T> Message<T> createMessage(Message<T> messageBeingWrapped, DataStructure data) {
		Map<String, T> parentData = messageBeingWrapped.getAll();
		Map<String, T> newData = data.getAll();
		if (parentData != null)
			newData.putAll(parentData);
		Message<T> rootMessage = RootMessage.getInstance();
		return new CommonMessage<>(Messages.Type.FLAT_MAP, rootMessage, data);
	}

	@Override
	public DataStructure designateData() {
		// TODO Auto-generated method stub
		return new DataStructureMap();
	}

}
