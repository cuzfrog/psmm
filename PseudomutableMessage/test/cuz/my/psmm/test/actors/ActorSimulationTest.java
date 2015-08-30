package cuz.my.psmm.test.actors;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cuz.my.psmm.Messages;
import cuz.my.psmm.MyAbstractTest;
import cuz.my.psmm.UMessage;

public class ActorSimulationTest extends MyAbstractTest {
	final static ActorSystem system = ActorSystem.create("test");
	final static List<ActorRef> actors = new ArrayList<>();

	private final static int ACTOR_AMOUNT = 100;
	private final static int VALUE_PAIR_AMOUNT = 1000;

	private void createActor(int howMany) {
		List<String> keys = keyList("actor", howMany); // create actors' names;
		for (int i = 0; i < howMany; i++) {
			final ActorRef actor = system.actorOf(Props.create(TestActor.class), keys.get(i));
			actor.tell(this, ActorRef.noSender()); //give actor 
			actors.add(actor);
		}
	}

	@Before
	public void setUp() throws Exception {
		initiate(6000);
		createActor(ACTOR_AMOUNT); //create actors

		initiatePairList("int", 30, VALUE_PAIR_AMOUNT); //immutable value pair list
	}

	@Test
	public void test() {
		//UMessage message=Messages.create().set(key, value)
		actors.get(0).tell(msg, ActorRef.noSender());
	}

}
