package cuz.my.psmm.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import cuz.my.psmm.PsmmConfiguration;
import cuz.my.psmm.Messages;
import cuz.my.psmm.PsmmSystem;
import cuz.my.psmm.UMessage;

class SimpleTest {

	public static void main(String[] args) throws NoSuchAlgorithmException,
			IOException {
		// TODO Auto-generated method stub
		PsmmSystem.initiate(new PsmmConfiguration(64));
		String key1 = "int1";
		String key2 = "int2";
		String key3 = "str1";
		UMessage rm = Messages.create(Messages.Type.CACHED_FLAT_MAP)
				.set(key1, 2).set(key2, 122).set(key3, "this is string")
				.cook();

		UMessage rmnew=rm.set(key1, 315).set(key2, 122).set(key3, "this is string")
				.cook();
		UMessage rmnew2=rm.set(key1, 315).set(key2, 122).set(key3, "this is string")
				.cook();
		
		System.out.println(rm+"|"+rm.get(key1));
		System.out.println(rmnew+"|"+rmnew.get(key1));
		System.out.println(rmnew2+"|"+rmnew2.get(key1));
		System.out.println(rmnew2.equals(rmnew));
		System.out.println(Arrays.equals(rm.getSignature(), rmnew.getSignature()));
	}
    
}
