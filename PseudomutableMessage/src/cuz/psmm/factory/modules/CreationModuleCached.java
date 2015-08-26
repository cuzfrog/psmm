package cuz.psmm.factory.modules;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cuz.psmm.exceptions.PsmmException;
import cuz.psmm.exceptions.PsmmMessageConstructionFailedException;
import cuz.psmm.factory.data.Data;
import cuz.psmm.message.Message;
import cuz.psmm.message.MessageHelper;

final class CreationModuleCached extends Module {


	public CreationModuleCached() {
		super(null,"Cached");
		// CreationModule is at the bottom of the chain.
	}

	@Override
	public <T> Message<T> createMessage(Message.Type type,Message<T> messageBeingWrapped,
			Data data) throws PsmmException {
		// TODO Auto-generated method stub
		byte[] signature = calculateSignature(
				type, messageBeingWrapped, data);
		
		Message<T> message;
		if((message=MessageHelper.seekMessage(signature))==null){
			message=MessageHelper.getConcretMessage(type, messageBeingWrapped, data, signature);
		}
		return message;
	}
	
	private static byte[] calculateSignature(Message.Type type, Message<?> parent,
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
