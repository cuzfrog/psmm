package cuz.psmm.message;

import cuz.psmm.exceptions.PsmmException;
import cuz.psmm.factory.datastructure.DataStructure;
import cuz.psmm.message.Message.Type;

final class CachedMessage<T> extends AbstractMessage<T>{


	private byte[] signature;
	
	public CachedMessage(Message.Type type, Message<T> parent, DataStructure data,byte[] signature) throws PsmmException {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
		this.signature=signature;
	}

	@Override
	public byte[] getSignature() {
		// TODO Auto-generated method stub
		return this.signature;
	}
}
