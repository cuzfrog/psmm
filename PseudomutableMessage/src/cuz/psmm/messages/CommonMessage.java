package cuz.psmm.messages;

import cuz.psmm.AbstractMessage;
import cuz.psmm.Message;
import cuz.psmm.Messages;
import cuz.psmm.factoryModules.DataStructure;

public final class CommonMessage<T> extends AbstractMessage<T> {

	public CommonMessage(Messages.Type type,Message<T> parent, DataStructure data) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
	}

	
}
