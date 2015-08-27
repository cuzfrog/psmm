package cuz.my.psmm;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cuz.my.psmm.data.Data;
import cuz.my.psmm.exceptions.PsmmException;
import cuz.my.psmm.exceptions.PsmmMessageConstructionFailedException;

final class CreationModuleCached extends Module {


	public CreationModuleCached() {
		super(null,"Cached");
		// CreationModule is at the bottom of the chain.
	}

	@Override
	public <T> TypedMessage<T> createMessage(Messages.Type type,TypedMessage<T> messageBeingWrapped,
			Data data) throws PsmmException {
		// TODO Auto-generated method stub
		byte[] signature = calculateSignature(
				type, messageBeingWrapped, data);
		
		TypedMessage<T> message;
		if((message=MessageHelper.seekMessage(signature))==null){
			message=MessageHelper.getConcretMessage(type, messageBeingWrapped, data, signature);
		}
		return message;
	}
	
	private static byte[] calculateSignature(Messages.Type type, TypedMessage<?> parent,
			Data data) throws PsmmException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(Integer.valueOf(type.ordinal()).byteValue());
			md.update(data.getSignature());
			md.update(parent.getSignature());
			return  md.digest();
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			throw new PsmmMessageConstructionFailedException();
		}
	}

}
