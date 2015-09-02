package cuz.my.psmm.test.actors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import akka.actor.ActorSystem;
import akka.actor.Props;

@RunWith(Suite.class)
@SuiteClasses({ TestControl.class, TestUntypedMessage.class,
		TestTypedMessage.class })
public class TestSuite extends TestAbstractActorSimulation {

	@BeforeClass
	public static void setUpAll() {
		initiate(6000);
		initiatePairList("int", 30, VALUE_PAIR_AMOUNT,Integer.MAX_VALUE); 
		// immutable value pair list
		initiateNameList("sender", ACTOR_AMOUNT); // create actors' names;
		system = ActorSystem.create("test");
		createActor(SenderInteger.class); // create actors
		system.actorOf(Props.create(Listener.class), "listener"); 
	}

	@AfterClass
	public static void tearDownAll() {
		actors.clear();
		system.shutdown();
		system = null;
	}
}
