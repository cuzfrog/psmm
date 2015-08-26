package cuz.psmm.message;

import cuz.psmm.factory.PsmmFactory;

final class RawMessageImpl<T> implements RawMessage<T> {

	private PsmmFactory psmmFactory;
	private Message<T> messageBeingWrapped;
	
	RawMessageImpl(PsmmFactory psmmFactory,Message<T> messageBeingWrapped) {
		super();
		this.psmmFactory = psmmFactory;
		this.messageBeingWrapped=messageBeingWrapped;
	}


	@Override
	public Message<T> cook() {
		// TODO Auto-generated method stub
		return   psmmFactory.commit(messageBeingWrapped);
	}

	@Override
	public RawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		psmmFactory.set(key, datum);
		return this;
	}

}
