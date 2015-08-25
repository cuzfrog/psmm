package cuz.psmm.messages;

import cuz.psmm.AbstractMessage;
import cuz.psmm.Message;
import cuz.psmm.Messages;
import cuz.psmm.factoryModules.DataStructure;

public class UnbufferedMessage<T> extends AbstractMessage<T> {

	public UnbufferedMessage(Messages.Type type,Message<T> parent, DataStructure data) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
	}

	
}
