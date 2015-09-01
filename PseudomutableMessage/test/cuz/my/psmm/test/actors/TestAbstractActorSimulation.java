package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Rule;
import org.junit.rules.TestName;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import cuz.my.psmm.Messages;
import cuz.my.psmm.Messages.Style;
import cuz.my.psmm.test.actors.threadinterface.SharedReadOnlyData;
import cuz.my.psmm.test.actors.threadinterface.ThreadTrigger;
import cuz.my.psmm.MyAbstractTest;
import cuz.my.psmm.Pair;
import cuz.my.psmm.TMessage;
import cuz.my.psmm.UMessage;
import cuz.my.psmm.UntypedRawMessage;

public class TestAbstractActorSimulation extends MyAbstractTest implements SharedReadOnlyData, ThreadTrigger {
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
		initiatePairList("int", 30, VALUE_PAIR_AMOUNT,Integer.MAX_VALUE); 
		// immutable value pair list
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

	private <T> void sendAndWaitForFinish(Parcel<T> parcel) {
		for (int i = 0; i < STARTING_ACTOR_AMOUNT; i++) {
			actors.get(i).tell(parcel, ActorRef.noSender());
		}
		// need to wait here, until all actors stop.
		awaitThreadFinish();
	}

	protected <T> void untypedTest(Style type) {
		@SuppressWarnings("unchecked")
		Pair<T> pair = (Pair<T>) randomPair();
		UntypedRawMessage newRawMessage;
		newRawMessage = Messages.create(type);
		T value = pair.getValue();
		if (value instanceof Integer) {
			newRawMessage.set(pair.getKey(), (int) pair.getValue());
		} else if (value instanceof Double) {
			newRawMessage.set(pair.getKey(), (double) pair.getValue());
		} else {
			newRawMessage.set(pair.getKey(), (String) pair.getValue());
		}
		
		UMessage newMessage = newRawMessage.cook();
		List<Pair<T>> dataPairs = new ArrayList<>();
		dataPairs.add(pair);
		Parcel<T> startVp = new ParcelVerification<>(newMessage, dataPairs);
		sendAndWaitForFinish(startVp);
	}

	protected <T> void typedTest(Style type,Class<T> c) {
		@SuppressWarnings("unchecked")
		Pair<T> pair = (Pair<T>) randomPair();
		TMessage<T> message;
		message = Messages.create(type,c).set(pair.getKey(), pair.getValue())
				.cook();
		List<Pair<T>> dataPairs = new ArrayList<>();
		dataPairs.add(pair);
		Parcel<T> startVp = new ParcelTypedVerification<>(message, dataPairs);
		sendAndWaitForFinish(startVp);
	}
	
	protected <T> void controlTest() {

		@SuppressWarnings("unchecked")
		Pair<T> pair = (Pair<T>) randomPair();
		List<Pair<T>> dataPairs = new ArrayList<>();
		dataPairs.add(pair);
		Map<String, T> map = new HashMap<>();
		map.put(pair.getKey(), pair.getValue());
		Parcel<T> startCp = new ParcelControl<>(map, dataPairs);
		sendAndWaitForFinish(startCp);
	}


	@Override
	public String randomName() {
		return names.get(ThreadLocalRandom.current().nextInt(names.size()));
	}

	@Override
	public SenderModule getModule() {
		// TODO Auto-generated method stub
		return senderModule;
	}
	@Override
	public void threadFailed() {
		threadFailKey.set(true);
	}

	@Override
	public void threadFinished() {
		threadFinishKey.set(true);
	}
}
