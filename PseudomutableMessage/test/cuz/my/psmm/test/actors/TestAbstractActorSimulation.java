package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.rules.TestName;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cuz.my.psmm.Messages;
import cuz.my.psmm.Messages.Style;
import cuz.my.psmm.MyAbstractTest;
import cuz.my.psmm.TMessage;
import cuz.my.psmm.UMessage;

public class TestAbstractActorSimulation extends MyAbstractTest {
	protected ActorSystem system;
	protected final static List<ActorRef> actors = new ArrayList<>();

	public final static int ACTOR_AMOUNT = 20;
	public final static long MESSAGE_TEST_AMOUNT = 500000;
	protected final static int VALUE_PAIR_AMOUNT = 1000;
	protected final static int STARTING_ACTOR_AMOUNT = 10; // how many actors
															// that
															// start work at the
	@Rule
	public TestName testName = new TestName(); // beginning.

	protected void createActor(Class<Sender> c) {
		List<String> keys = nameList();
		for (String key : keys) {
			final ActorRef actor = system.actorOf(Props.create(c), key);
			actor.tell(this, ActorRef.noSender());
			// give actor interface SharedReadOnlyData to read MyAbstractTest
			actors.add(actor);
		}

	}

	public void setUp() throws Exception {
		system = ActorSystem.create("test");
		initiate(6000);
		initiatePairList("int", 30, VALUE_PAIR_AMOUNT); // immutable value pair
														// list
		initiateNameList("sender", ACTOR_AMOUNT); // create actors' names;
		createActor(Sender.class); // create actors
		final ActorRef listener = system.actorOf(Props.create(Listener.class),
				"listener"); // create listener
		listener.tell(this, ActorRef.noSender());
	}

	public void tearDown() {
		system.shutdown();
		system = null;
		actors.clear();
		logger.info("{} completed.", testName.getMethodName());
	}

	private void sendAndWaitForFinish(Parcel parcel) {
		for (int i = 0; i < STARTING_ACTOR_AMOUNT; i++) {
			actors.get(i).tell(parcel, ActorRef.noSender());
		}
		// need to wait here, until all actors stop.
		awaitThreadFinish();
	}

	protected void untypedTest(Style type) {
		Pair pair = randomPair();
		UMessage message;
		message = Messages.create(type).set(pair.getKey(), pair.getValue())
				.cook();
		List<Pair> dataPairs = new ArrayList<>();
		dataPairs.add(pair);
		Parcel startVp = new ParcelVerification(message, dataPairs);
		sendAndWaitForFinish(startVp);
	}

	protected void typedTest(Style type) {
		Pair pair = randomPair();
		TMessage<Integer> message;
		message = Messages.create(type,Integer.class).set(pair.getKey(), pair.getValue())
				.cook();
		List<Pair> dataPairs = new ArrayList<>();
		dataPairs.add(pair);
		Parcel startVp = new ParcelTypedVerification<>(message, dataPairs);
		sendAndWaitForFinish(startVp);
	}
	
	protected void controlTest() {

		Pair pair = randomPair();
		List<Pair> dataPairs = new ArrayList<>();
		dataPairs.add(pair);
		Map<String, Integer> map = new HashMap<>();
		map.put(pair.getKey(), pair.getValue());
		Parcel startCp = new ParcelControl(map, dataPairs);
		sendAndWaitForFinish(startCp);
	}


	

}
