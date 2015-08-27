package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class CachedMessage<T> extends AbstractMessage<T>{


	private byte[] signature;
	
	public CachedMessage(Messages.Type type, TypedMessage<T> parent, Data data,byte[] signature) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
		this.signature=signature;
	}

	@Override
	public byte[] getSignature() {
		return this.signature;
	}
}
