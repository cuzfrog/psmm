package cuz.psmm.message;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cuz.psmm.exceptions.PsmmException;
import cuz.psmm.exceptions.PsmmMessageConstructionFailedException;
import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.datastructure.DataStructure;

public final class MessageHelper {
	

	private static Map<byte[], Message<?>> messagePool = new ConcurrentHashMap<>();

	private MessageHelper() {
	}

	static <T> RawMessage<T> fetch(Message.Type type, Message<T> messageBeingWrapped) {
		return new RawMessageImpl<>(PsmmFactory.seekFactory(type),
				messageBeingWrapped);
	}

	@SuppressWarnings("unchecked")
	public static <T> Message<T> seekMessage(byte[] signature) {
		return (Message<T>) messagePool.get(signature);
	}

	public static void putMessage(byte[] signature, Message<?> message) {
		messagePool.put(signature, message);
	}

	public static byte[] calculateSignature(Message.Type type, Message<?> parent,
			DataStructure data) throws PsmmException {
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
	
	public static <T> Message<T> getConcretMessage(Message.Type type){
		swich
	}
}
