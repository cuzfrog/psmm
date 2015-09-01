package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import akka.actor.UntypedActor;
import cuz.my.psmm.MyAbstractTest.Pair;
import cuz.my.psmm.SharedReadOnlyData;

public class Sender extends UntypedActor {

	private SharedReadOnlyData dataSource;
	private boolean working = false;
	private SenderModule module;

	private Parcel randomGenerateMessage(Parcel receivedParcel) {
		int dataAmount = ThreadLocalRandom.current().nextInt(5); // up to 5 pair
																	// of data
		List<Pair> dataPairs = new ArrayList<>();
		List<String> keysPreceding = new ArrayList<>();
		for (int i = 0; i < dataAmount;) {
			Pair pair = dataSource.randomPair();

			if (!keysPreceding.contains(pair.getKey())) {
				keysPreceding.add(pair.getKey());
				dataPairs.add(pair);
				i++;
			}
		} // prepare data pairs
		
	

		return module.parcel(dataPairs, receivedParcel);

	}

	private void randomSend(Parcel newVp) {
		context().actorSelection("../" + dataSource.randomName()).tell(newVp,
				getSelf());
		context().actorSelection("../listener").tell(new InstructionCount(),
				self());
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		if (arg0 instanceof Parcel && working) {

			Parcel receivedVp = (Parcel) arg0;
			if (receivedVp.verify()) {
				Parcel vp = randomGenerateMessage(receivedVp);
				randomSend(vp);

			} else {
				context().actorSelection("../listener").tell(receivedVp,
						getSender()); // when
										// error
			}
		} else if (arg0 instanceof SharedReadOnlyData) {
			dataSource = (SharedReadOnlyData) arg0; // when initiating
			module=dataSource.getModule();
			working = true;
		} else if (arg0 instanceof InstructionStop) {
			working = false;
			context().actorSelection("../listener").tell(
					new InstructionStopConfirmed(), getSender());
			// terminate self:
		} else {
			unhandled(arg0);
		}
	}

}
