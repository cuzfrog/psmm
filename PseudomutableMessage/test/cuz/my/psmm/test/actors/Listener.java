package cuz.my.psmm.test.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.UntypedActor;
import cuz.my.psmm.test.actors.threadinterface.ThreadTrigger;

public class Listener extends UntypedActor {

	private Logger logger = LoggerFactory.getLogger("listener");
	private boolean working = false;
	private long messageCount = 0;
	private long actorWorkingCount = TestAbstractActorSimulation.ACTOR_AMOUNT;
	private ThreadTrigger threadTrigger;

	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		if (arg0 instanceof InstructionCount && working) {
			if (++messageCount > TestAbstractActorSimulation.MESSAGE_TEST_AMOUNT) {
				// on end:
				working = false;
				context().actorSelection("/user/*").tell(new InstructionStop(), self());
				//logger.info("Message sending completed.");
			} else if (messageCount % (TestAbstractActorSimulation.MESSAGE_TEST_AMOUNT / 5) == 0) {
				//logger.info(messageCount + " messages have sent.");
			}
		} else if (arg0 instanceof ParcelVerification && working) {
			// on error:
			working = false;
			threadTrigger.threadFailed();
			context().system().shutdown();
			logger.error("Verification failed:");
		} else if (arg0 instanceof ThreadTrigger) {
			working = true;
			threadTrigger = (ThreadTrigger) arg0;
		} else if (arg0 instanceof InstructionStopConfirmed) {
			if (--actorWorkingCount == 0) {
				threadTrigger.threadFinished();
				logger.info("{} messages sent,Actors shutdown!",messageCount);
			}
			//logger.info("working senders:{}",actorWorkingCount);
		} else {
			unhandled(arg0);
		}
	}

}
