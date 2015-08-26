package cuz.psmm.message;

import cuz.psmm.factory.datastructure.DataStructure;

final class CommonMessage<T> extends AbstractMessage<T> {

	public CommonMessage(Message.Type type,Message<T> parent, DataStructure data) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
	}

	
}
