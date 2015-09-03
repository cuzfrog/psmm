package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import akka.actor.UntypedActor;
import cuz.my.psmm.Pair;

abstract class Sender extends UntypedActor {

	protected SharedReadOnlyData dataSource;
	protected boolean working = false;
	protected SenderModule module;
	
	protected <T> Parcel<T> randomGenerateMessage(Parcel<T> receivedParcel) {
		int dataAmount = ThreadLocalRandom.current().nextInt(5); // up to 5 pair
																	// of data
		List<Pair<T>> dataPairs = new ArrayList<>();
		List<String> keysPreceding = new ArrayList<>();
		for (int i = 0; i < dataAmount;) {
			@SuppressWarnings("unchecked")
			Pair<T> pair = (Pair<T>) dataSource.randomPair();

			if (!keysPreceding.contains(pair.getKey())) {
				keysPreceding.add(pair.getKey());
				dataPairs.add(pair);
				i++;
			}
		} // prepare data pairs
		
	

		return module.parcel(dataPairs, receivedParcel);

	}

	protected <T> void randomSend(Parcel<T> newVp) {
		context().actorSelection("../" + dataSource.randomName()).tell(newVp,
				getSelf());
		context().actorSelection("../listener").tell(new InstructionCount(),
				self());
	}
}
