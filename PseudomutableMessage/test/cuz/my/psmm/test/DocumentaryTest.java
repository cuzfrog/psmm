package cuz.my.psmm.test;

import cuz.my.psmm.Messages;
import cuz.my.psmm.PsmmConfiguration;
import cuz.my.psmm.PsmmConfiguration.FactoryPoolType;
import cuz.my.psmm.PsmmSystem;
import cuz.my.psmm.TMessage;
import cuz.my.psmm.UMessage;

class DocumentaryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UMessage message=
				Messages.create(Messages.Style.FLAT_MAP)
				.set("key1", "string value")
				.cook();
		
		TMessage<Integer> message2=
				Messages.create(Integer.class)
				.set("key1", 583)
				//.set("key2", "string value") //compile time error
				.cook();
		
		
		PsmmConfiguration config=new PsmmConfiguration()
				        .setFactoryPoolChoseType(FactoryPoolType.NULL)
						.setFactoryPoolSize(24);
		PsmmSystem.initiate(config);
	}


	
}
