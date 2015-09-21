package com.github.cuzfrog.psmm.test;

import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.PsmmConfiguration;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.TBuilder;
import com.github.cuzfrog.psmm.TMessage;
import com.github.cuzfrog.psmm.UMessage;
import com.github.cuzfrog.psmm.PsmmConfiguration.FactoryPoolType;

class DocumentaryTest {

	@SuppressWarnings({ "unused", "boxing" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PsmmSystem.initiate();
		UMessage message = Messages.builder(Messages.Style.FLAT_MAP).set("key1", "string value").build();
		TBuilder<String, Integer> b2 = Messages.typedBuilder();
		TMessage<String, Integer> message2 = b2.set("key1", 583)
				// .set("key2", "string value") //compile time error
				.build();

		PsmmConfiguration config = new PsmmConfiguration().setFactoryPoolChoseType(FactoryPoolType.NULL)
				.setMessageMaxDepth(64);
		PsmmSystem.initiate(config);
		
		
	}

}
