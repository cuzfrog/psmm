package com.github.cuzfrog.psmm.test.actors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import akka.actor.ActorSystem;
import akka.actor.Props;

@RunWith(Suite.class)
@SuiteClasses({ TestUntypedMessage.class,
		TestTypedMessage.class,TestControl.class })
public class TestSuite extends TestAbstractActorSimulation {

	@BeforeClass
	public static void setUpAll() {
		
		initiatePairList(VALUE_NAME, VALUE_KEY_AMOUNT , VALUE_PAIR_AMOUNT,VALUE_UPBOUND); 
		// immutable value pair list
		initiateNameList("sender", ACTOR_AMOUNT); // create actors' names;
		system = ActorSystem.create("test");
		createActor(SenderInteger.class); // create actors
		system.actorOf(Props.create(Listener.class), "listener"); 
	}

	@AfterClass
	public static void tearDownAll() {
		senders.clear();
		system.shutdown();
		system = null;
	}
}
