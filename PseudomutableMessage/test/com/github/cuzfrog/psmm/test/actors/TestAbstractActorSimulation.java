package com.github.cuzfrog.psmm.test.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Rule;
import org.junit.rules.TestName;

import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.MyAbstractTest;
import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.PsmmConfiguration;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.TMessage;
import com.github.cuzfrog.psmm.UMessage;
import com.github.cuzfrog.psmm.UntypedRawMessage;
import com.github.cuzfrog.psmm.Messages.Style;
import com.github.cuzfrog.psmm.PsmmConfiguration.FactoryPoolType;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * This is the main function class for actor test.
 * 
 * <p>
 * Use each constant to control the test. Actor named "listener" is used to
 * supervise, count and stop test process, while "sender"s send message to each
 * other.
 * <p>
 * Actors communicate with main thread through two AtomicBoolean flag.
 * <p>
 * Value pairs are randomly generate keys and values. They are read-only, when
 * senders invoke interface {@link SharedReadOnlyData#randomPair()} to simulate
 * data producing process.
 * <p>
 * The amount of actors, actors that parallel send messages, value pairs, value
 * keys and including value range are essential to the results of each test.
 * <p>
 * Parcel is an alias of actor message, but encapsulates a Psmm message and a
 * common HashMap. Which is intended to verify if the message-exhibited data is
 * the same with data in the according map.
 * 
 * @author Cause Chung
 *
 */
public class TestAbstractActorSimulation extends MyAbstractTest implements SharedReadOnlyData, ThreadTrigger {
	

	protected static ActorSystem system;
	protected final static List<ActorRef> senders = new ArrayList<>();

	/**
	 * JUnit fail flag, every actor can trigger to fail this test through
	 * interface {@link ThreadTrigger}
	 */
	protected AtomicBoolean threadFailKey = new AtomicBoolean(false);
	/**
	 * Main thread waiting barrier, listener tell main thread to go on through
	 * interface {@link ThreadTrigger}
	 */
	protected AtomicBoolean threadFinishKey = new AtomicBoolean(false);
	/** Sender strategy pattern composed object */
	protected SenderModule senderModule;

	// test parameters(some are self-explanatory), which are essential to the
	// results of each test:
	public final static int ACTOR_AMOUNT = 20;
	public final static long MESSAGE_TEST_AMOUNT = 1000000;
	/**
	 * Value key's name. Must be one of "int" for integer, "double" for double
	 * or whatever would be interpreted as String. {@code Pair<T>} will be
	 * initiated depending on the name. Here it uses the UUID to simulate
	 * a random string.
	 */
	protected final static String VALUE_NAME = "int";
	protected final static int VALUE_PAIR_AMOUNT = 1000;
	/**
	 * For psmm, the more the keys are, the lower the possibility to set and
	 * overwrite the same value.
	 */
	protected final static int VALUE_KEY_AMOUNT = 30;
	/**
	 * For cached psmm, the fewer the possible values are, the more likely it
	 * hits the cache. For string value, the max up-bond is the length of a UUID.
	 */
	protected final static int VALUE_UPBOUND = 10000;
	//protected final static int VALUE_UPBOUND = Integer.MAX_VALUE;
	/**
	 * The amount of senders that parallel send message at the beginning.
	 */
	protected final static int STARTING_ACTOR_AMOUNT = 10;
	@Rule
	public TestName testName = new TestName();

	// before
	public void setUp() throws Exception {
		PsmmConfiguration config=new PsmmConfiguration();
		//config.setFactoryPoolChoseType(FactoryPoolType.NULL); //test no factory pool
		PsmmSystem.initiate(config);
		//initiate Psmm with message cache capacity.
		threadFinishKey.set(false);
		system.actorSelection("/user/listener").tell(this, ActorRef.noSender());
		system.actorSelection("/user/*").tell(this, ActorRef.noSender());
		// give actor interface SharedReadOnlyData to read MyAbstractTest
	}

	// after
	public void tearDown() {
		logger.info("{} completed.", testName.getMethodName());
	}

	protected static void createActor(Class<? extends Sender> c) {
		List<String> keys = nameList();
		for (String key : keys) {
			final ActorRef actor = system.actorOf(Props.create(c), key);
			senders.add(actor);
		}

	}

	private <T> void sendAndWaitForFinish(Parcel<T> parcel) {
		for (int i = 0; i < STARTING_ACTOR_AMOUNT; i++) {
			senders.get(i).tell(parcel, ActorRef.noSender());
		}
		// need to wait here, until all actors stop.
		while (!threadFinishKey.get()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}

	protected <T> void untypedTest(Style type) {
		@SuppressWarnings("unchecked")
		Pair<T> pair = (Pair<T>) randomPair();
		UntypedRawMessage newRawMessage = Messages.create(type);
		T value = pair.getValue();
		if (value instanceof Integer) {
			newRawMessage.set(pair.getKey(), (Integer) pair.getValue());
		} else if (value instanceof Double) {
			newRawMessage.set(pair.getKey(), (Double) pair.getValue());
		} else {
			newRawMessage.set(pair.getKey(), (String) pair.getValue());
		}

		UMessage newMessage = newRawMessage.cook();
		List<Pair<T>> dataPairs = new ArrayList<>();
		dataPairs.add(pair);
		Parcel<T> startVp = new ParcelVerification<>(newMessage, dataPairs);
		sendAndWaitForFinish(startVp);
	}

	protected <T> void typedTest(Style type, Class<T> c) {
		@SuppressWarnings("unchecked")
		Pair<T> pair = (Pair<T>) randomPair();
		TMessage<T> message = Messages.create(type, c).set(pair.getKey(), pair.getValue()).cook();
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
	
	@Override
	public Pair<?> randomPair() {
		// TODO Auto-generated method stub
		return super.staticRandomPair();
	}
}
