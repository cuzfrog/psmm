package cuz.my.psmm;

final class RetainedMessage<T> extends AbstractMessage<T>{

	private static final long serialVersionUID = 1L;
	private final Signature signature;
	
	public RetainedMessage(Messages.Style type, MessageAdaptorInterface<T> parent, Data data,Signature signature) {
		super(type, parent, data);
		this.signature=signature;
	}

	@Override
	public byte[] getSignature() {
		return this.signature.getSignature();
	}
	
}
