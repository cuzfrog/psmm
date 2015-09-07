package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class CachedMessage<T> extends AbstractMessage<T>{

    //speedup hashcode calculation
	private final Signature signature;
	
	public CachedMessage(Messages.Style type, Message<T> parent, Data data,Signature signature) {
		super(type, parent, data);
		this.signature=signature;
	}

	@Override
	public byte[] getSignature() {
		// TODO Auto-generated method stub
		return this.signature.getSignature();
	}
	
}
