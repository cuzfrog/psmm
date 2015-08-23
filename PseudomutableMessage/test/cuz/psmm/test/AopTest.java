package cuz.psmm.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cuz.psmm.Message;
import cuz.psmm.RawMessage;

public class AopTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		String key="int1";
		Message<Integer> rm=Message.create(Message.Type.LINKED,Integer.class).set(key,315).done();
		Message<Integer> message=ctx.getBean("rawMessage",RawMessage.class).set(key, 314).done();
		System.out.println(message.get(key));

		ctx.close();
		
		
		
	}
	

}
