package cuz.psmm.messages;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cuz.psmm.AbstractMessage;
import cuz.psmm.Message;
import cuz.psmm.Messages.Type;
import cuz.psmm.exceptions.PsmmException;
import cuz.psmm.exceptions.PsmmMessageConstructionFailedException;
import cuz.psmm.factoryModules.DataStructure;

public final class CachedMessage<T> extends AbstractMessage<T>{


	private byte[] signature;
	
	public CachedMessage(Type type, Message<T> parent, DataStructure data) throws PsmmException {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
		try {
			MessageDigest md=MessageDigest.getInstance("SHA1");
			md.update(Integer.valueOf(type.ordinal()).byteValue());
			md.update(data.getSignature());
			md.update(parent.getSignature());
			this.signature=md.digest();
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			throw new PsmmMessageConstructionFailedException();
		}
		
	}

	@Override
	public byte[] getSignature() {
		// TODO Auto-generated method stub
		return this.signature;
	}
}
