package cuz.my.psmm.test.actors;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.typesafe.config.ConfigException.Generic;

import scala.collection.generic.GenericSeqCompanion;
import cuz.my.psmm.Messages;
import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UMessage;
import cuz.my.psmm.UntypedRawMessage;
import cuz.my.psmm.MyAbstractTest.Pair;

public class SenderModuleTypedMessage<T> implements SenderModule {

	@Override
	public Parcel parcel(List<Pair> dataPairs, Parcel receivedParcel) {
		// TODO Auto-generated method stub
		TypedRawMessage<T> newRawMessage;
		int odds = ThreadLocalRandom.current().nextInt(100);
		// 30% possibility to new
		if (odds < 30) {
			newRawMessage = Messages.create();
		} else {
			newRawMessage = receivedParcel.getMessage();
		} // prepare raw message

		for (Pair pair : dataPairs) {
			newRawMessage.set(pair.getKey(), pair.getValue());
		}
		UMessage newMessage = newRawMessage.cook(); // set and cook raw message

		return new ParcelVerification(newMessage, dataPairs);
	}

}
