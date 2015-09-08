package cuz.my.psmm;

final class CachedMessage<T> extends AbstractMessage<T>{

	private static final long serialVersionUID = 1L;
	private final Signature signature;
	
	public CachedMessage(Messages.Style type, MessageAdaptorInterface<T> parent, Data data,Signature signature) {
		super(type, parent, data);
		this.signature=signature;
	}

	@Override
	public byte[] getSignature() {
		return this.signature.getSignature();
	}
	
}
