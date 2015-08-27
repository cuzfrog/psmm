package cuz.my.psmm.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class AopTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

		ctx.close();
		
		
	}
	

}
