package cuz.psmm.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cuz.psmm.message.Message;

public class SimpleTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub

		String key1 = "int1";
		String key2 = "int2";
		Message<Integer> rm = Message
				.create(Message.Type.CACHED_FLAT_MAP, Integer.class)
				.set(key1, 315)
				.set(key2, 122)
				.cook();
		
		System.out.println(rm.get(key2));
		System.out.println(rm.get(key1));
		System.out.println(rm.getSignature());
		rm=rm.set(key1, 678).cook();
		System.out.println(rm.get(key1));
		rm.getAll().get(key2);
		System.out.println(rm.get(key2));
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		MessageDigest md=MessageDigest.getInstance("SHA1");
		byte[] result=md.digest(key1.getBytes("UTF8"));
		outputStream.write(result);
		outputStream.write(result);
		System.out.println(outputStream.size());
		
		Number n=Double.valueOf(42342345345546756245.999d);
		System.out.println(n.doubleValue());

	}

}
