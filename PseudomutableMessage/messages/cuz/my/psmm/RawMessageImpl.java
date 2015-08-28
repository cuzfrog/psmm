package cuz.my.psmm;

import cuz.my.psmm.PsmmFactory;

final class RawMessageImpl<T> extends AbstractRawMessage<T>  {

	RawMessageImpl(PsmmFactory psmmFactory,TMessage<T> messageBeingWrapped) {
		super();
		this.psmmFactory = psmmFactory;
		this.messageBeingWrapped=messageBeingWrapped;
	}

}
