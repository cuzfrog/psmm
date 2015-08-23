package cuz.psmm.test;

import cuz.psmm.Message;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String key="int1";
		Message<Integer> rm=Message.create(Message.Type.LINKED,Integer.class).set(key,315).done();
		System.out.println(rm.get(key));
				
	}

}
