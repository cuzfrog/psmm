package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import akka.actor.UntypedActor;
import cuz.my.psmm.Messages;
import cuz.my.psmm.MyAbstractTest.Pair;
import cuz.my.psmm.SharedReadOnlyData;
import cuz.my.psmm.UMessage;
import cuz.my.psmm.UntypedRawMessage;

public class Sender extends UntypedActor {

	private SharedReadOnlyData dataSource;
	private boolean working=false;

	private VerificationPackage randomGenerateMessage(
			VerificationPackage receivedVp) {
		UntypedRawMessage newRawMessage;

		int dataAmount = ThreadLocalRandom.current().nextInt(5); // up to 5 pair
																	// of data
		List<Pair> dataPairs = new ArrayList<>();
		for (int i = 0; i < dataAmount; i++) {
			dataPairs.add(dataSource.randomPair());
		} // prepare data pairs

		int odds = ThreadLocalRandom.current().nextInt(100);
		if (odds < 30) {
			newRawMessage = Messages.create();
		} else {
			newRawMessage = receivedVp.getMessage().raw();
		} // prepare raw message

		for (Pair pair : dataPairs) {
			newRawMessage.set(pair.getKey(), pair.getValue());
		}
		UMessage newMessage = newRawMessage.cook(); // set and cook raw message

		return new VerificationPackage(newMessage, dataPairs);

	}

	private void randomSend(VerificationPackage newVp) {
		context().actorSelection("../"+dataSource.randomName())
				.tell(newVp, getSelf());
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		if (arg0 instanceof VerificationPackage && working) {

			VerificationPackage receivedVp = (VerificationPackage) arg0;
			if (receivedVp.verify()) {
				VerificationPackage vp = randomGenerateMessage(receivedVp);
				randomSend(vp);
				context().actorSelection("../listener").tell(Integer.valueOf(1),self()); //count message
			} else {
				context().actorSelection("../listener").tell(receivedVp,
						getSender());  //when error
			} 
		} else if (arg0 instanceof SharedReadOnlyData) {
			dataSource = (SharedReadOnlyData) arg0; // when initiating
			working=true;
		} else if (arg0 instanceof InstructionStop){
			working=false;
			//terminate self:
			context().stop(self());
		}else {
			unhandled(arg0);
		}
	}

}
