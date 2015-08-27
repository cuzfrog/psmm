package cuz.my.psmm.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cuz.my.psmm.Messages;
import cuz.my.psmm.Module;
import cuz.my.psmm.UntypedMessage;

class SimpleTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub

		String key1 = "int1";
		String key2 = "int2";
		String key3 = "str1";
		UntypedMessage rm = Messages
				.create(Messages.Type.CACHED_FLAT_MAP)
				.set(key1, 315)
				.set(key2, 122)
				.set(key3, "this is string")
				.cook();
			
        System.out.println(rm.get(key2));
        System.out.println(rm.get(key3));
        
        
	}

}
