package cuz.psmm.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cuz.psmm.Message;
import cuz.psmm.RawMessage;

public class AopTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

		ctx.close();
		
		
		
	}
	

}
