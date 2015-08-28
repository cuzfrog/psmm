package cuz.my.psmm;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import cuz.my.psmm.data.Data;
import cuz.my.psmm.exceptions.PsmmException;
import cuz.my.psmm.exceptions.PsmmMessageConstructionFailedException;

final class CreationModuleCached extends Module {


	public CreationModuleCached() {
		super(null,"Cached");
		// CreationModule is at the bottom of the chain.
	}

	@Override
	public <T> TMessage<T> createMessage(Messages.Type type,TMessage<T> messageBeingWrapped,
			Data data) throws PsmmException {
		// TODO Auto-generated method stub
		Signature signature = calculateSignature(
				type, messageBeingWrapped, data);
		
		TMessage<T> message;
		if((message=PsmmSystem.seekMessage(signature))==null){
			message=PsmmSystem.getConcretMessage(type, messageBeingWrapped, data, signature);
		}
		
		return message;
	}
	
	//A message's signature is only associated with type and the data it exhibits.
	private static <T> Signature calculateSignature(Messages.Type type, TMessage<T> parent,
			Data data) throws PsmmException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			
			Map<String,T> parentMap=parent.getAll();
			Map<String,T> dataMap=data.getAll();
			
			if(parentMap==null){
				md.update(data.getDataStream());
			}else{
				parentMap.putAll(dataMap);
				md.update(Data.getDataStream(parentMap));
			}
			
			md.update(Integer.valueOf(type.ordinal()).byteValue());		
			return  new Signature(md.digest()); //here is about to modify
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			throw new PsmmMessageConstructionFailedException();
		}
	}
	

}
