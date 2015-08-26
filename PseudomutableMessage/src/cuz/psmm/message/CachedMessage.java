package cuz.psmm.message;

import cuz.psmm.factory.data.Data;

final class CachedMessage<T> extends AbstractMessage<T>{


	private byte[] signature;
	
	public CachedMessage(Message.Type type, Message<T> parent, Data data,byte[] signature) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
		this.signature=signature;
	}

	@Override
	public byte[] getSignature() {
		return this.signature;
	}
}
