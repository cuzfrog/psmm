package cuz.psmm.factoryModules;

import java.util.Map;

import cuz.psmm.Message;
import cuz.psmm.Messages;
import cuz.psmm.accessoaries.StateLess;
import cuz.psmm.messages.RootMessage;
import cuz.psmm.messages.UnbufferedMessage;
@StateLess
class FlatMapModule implements Module {



	@Override
	public <T> Message<T> createMessage(Message<T> messageBeingWrapped,
			DataStructure data) {
		Map<String, T> flatData = messageBeingWrapped.getAll();
		Map<String, T> newData=data.getAll();
		flatData.putAll(newData);
		Message<T> rootMessage=RootMessage.getInstance();
		return new UnbufferedMessage<T>(Messages.Type.FLAT_MAP,
				rootMessage, data);
	}

	@Override
	public DataStructure designateData() {
		// TODO Auto-generated method stub
		return new DataStructureMap();
	}

}
