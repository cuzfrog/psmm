package com.github.cuzfrog.psmm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.PsmmConfiguration;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.UMessage;

public class FactoryTrimmingTest {

	@BeforeClass
	public static void setupAll(){
		PsmmConfiguration config=new PsmmConfiguration();
		config.setFactoryPoolCheckInterval(2);
		config.setFactoryPoolSize(2);
		PsmmSystem.initiate(config);
	}
	
	
	@Test
	public void test() {
		ExecutorService executor=Executors.newFixedThreadPool(8);
		
		for(int i=0;i<8;i++){
			executor.execute(new Runnable(){

				@Override
				public void run() {
					for(int j=0;j<20;j++){
					UMessage msg=Messages.create().set("t1", "v").cook();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					msg.set("t2", "ss").cook();
					}
				}
				
			});
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
