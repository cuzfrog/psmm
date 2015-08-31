package cuz.my.psmm.test.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cuz.my.psmm.ThreadFailTrigger;
import akka.actor.UntypedActor;

public class Listener extends UntypedActor {

	private Logger logger = LoggerFactory.getLogger("listener");
	
	private final static long MESSAGE_TEST_AMOUNT=50;
	private long messageCount=0;
	private ThreadFailTrigger failTrigger;
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		// TODO Auto-generated method stub
		if(arg0 instanceof Integer){
			messageCount++;
			if(messageCount>MESSAGE_TEST_AMOUNT){
				//on end:
				//context().actorSelection("/user/test/sender*").tell(new InstructionStop(), self());
				//context().stop(getSelf());
				context().system().shutdown();
				context().stop(getSelf());
				logger.info("Message sending completed.");
			}else if(messageCount%(MESSAGE_TEST_AMOUNT/10)==0){
				logger.info(messageCount+" messages have sent.");
			}
		}else if(arg0 instanceof VerificationPackage){
			//on error:
			//context().actorSelection("/user/test/sender*").tell(new InstructionStop(), self());
			//context().stop(getSelf());
			failTrigger.threadFailed();
			context().system().shutdown();
			logger.error("Verification failed.");
		}else if(arg0 instanceof ThreadFailTrigger){
			failTrigger=(ThreadFailTrigger) arg0;
		}else {
			unhandled(arg0);
		}
	}

}
