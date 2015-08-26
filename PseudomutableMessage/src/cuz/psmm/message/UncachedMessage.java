package cuz.psmm.message;

import cuz.psmm.factory.data.Data;

final class UncachedMessage<T> extends AbstractMessage<T> {

	public UncachedMessage(Message.Type type,Message<T> parent, Data data) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
	}

	
}
