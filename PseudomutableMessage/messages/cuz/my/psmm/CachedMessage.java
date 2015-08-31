package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class CachedMessage<T> extends AbstractMessage<T>{


	private Signature signature;
	
	public CachedMessage(Messages.Style type, TMessage<T> parent, Data data,Signature signature) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
		this.signature=signature;
	}

	@Override
	public byte[] getSignature() {
		return this.signature.getSignature();
	}
}
