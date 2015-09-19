package com.github.cuzfrog.psmm.test;

import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.UMessage;
import com.github.cuzfrog.psmm.exceptions.PsmmCannotRegressExeption;

class SimpleTest {

	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PsmmSystem.initiate(); //only invoke once in your main thread
		
		
		//create a new message:
		String key1 = "int1";
		String key2 = "int2";
		String key3 = "str1";
		UMessage message1 = 
				Messages.create() //create a raw message
				.set(key1, 2)
				.set(key2, 122)
				.set(key3, "this is string") //add some data
				.build(); //without cook() compile time error.
		//after passed to another actor, fetch data:
		System.out.println(message1.get(key1) 
				+ "|" + message1.get(key2) 
				+ "|" + message1.get(key3));
		//result: 2|122|this is string
		
		String key4 = "double1";
		//"modify" last message, reset some data, add some new data:
		UMessage message2 =
				message1
				.set(key2, 45)
				.set(key3, "this is another string")
				.set(key4, 568.3d)
				.build();
		//after passed to another actor, fetch data:
		System.out.println(message2.get(key1) 
				+ "|" + message2.get(key2) 
				+ "|" + message2.get(key3)
				+ "|" + message2.get(key4));
		//result: 2|45|this is another string|568.3
		
		UMessage message3 = null;
		try {
			message3=message2.regress();
		} catch (PsmmCannotRegressExeption e) {
			e.printStackTrace();
		}
		System.out.println(message3.get(key1) 
				+ "|" + message3.get(key2) 
				+ "|" + message3.get(key3)
				+ "|" + message3.get(key4));
		//result: 2|122|this is string|null
	}

}
