package cuz.psmm.test;

import cuz.psmm.Message;
import cuz.psmm.Messages;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String key1 = "int1";
		String key2 = "int2";
		Message<Integer> rm = Messages
				.create(Messages.Type.LINKED_MAP, Integer.class)
				.set(key1, 315)
				.set(key2, 122)
				.cook();
		System.out.println(rm.get(key2));

	}

}
