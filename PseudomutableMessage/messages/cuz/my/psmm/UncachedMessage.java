package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class UncachedMessage<T> extends AbstractMessage<T> {

	public UncachedMessage(Messages.Type type,TypedMessage<T> parent, Data data) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
	}

	
}
