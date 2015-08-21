package cuz.psmm.test;

import cuz.psmm.Message;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Message message=Message.create(Message.Type.COMMON).set("int1", 314).done();
		System.out.println(message.get("int1"));
	}
	

}
