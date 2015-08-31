package cuz.my.psmm.test.actors;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cuz.my.psmm.Messages;
import cuz.my.psmm.Messages.Style;
import cuz.my.psmm.MyAbstractTest;
import cuz.my.psmm.UMessage;

public class ActorSimulationTest extends MyAbstractTest {
	final static ActorSystem system = ActorSystem.create("test");
	final static List<ActorRef> actors = new ArrayList<>();

	private final static int ACTOR_AMOUNT = 100;
	private final static int VALUE_PAIR_AMOUNT = 1000;

	private void createActor(Class<Sender> c) {
		List<String> keys = nameList();
		for (String key : keys) {
			final ActorRef actor = system.actorOf(Props.create(c), key);
			actor.tell( this, ActorRef.noSender());
			// give actor interface SharedReadOnlyData to read MyAbstractTest
			actors.add(actor);
		}

	}

	@Before
	public void setUp() throws Exception {
		initiate(6000);
		initiatePairList("int", 30, VALUE_PAIR_AMOUNT); // immutable value pair
														// list
		initiateNameList("sender", ACTOR_AMOUNT); // create actors' names;
		createActor(Sender.class); // create actors
		final ActorRef listener=system.actorOf(Props.create(Listener.class), "listener"); //create listener
		listener.tell(this,  ActorRef.noSender());
	}

	private void test(Style type) {
		
		Pair pair = randomPair();
		UMessage message;
		
			message = Messages.create(type).set(pair.getKey(), pair.getValue())
					.cook();
		
		List<Pair> dataPairs=new ArrayList<>();
		dataPairs.add(pair);
		VerificationPackage startVp=new VerificationPackage(message, dataPairs);
		actors.get(0).tell(startVp, ActorRef.noSender());

		// need to wait here, until all actors stop.
		system.awaitTermination();
	}

	@Test 
	public void testUntypedLinkedMap() {
		

		test(Style.LINKED_MAP);
		assertFalse(threadFailKey.get());
	}

}
