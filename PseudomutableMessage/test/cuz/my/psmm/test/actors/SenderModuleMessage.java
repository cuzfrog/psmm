package cuz.my.psmm.test.actors;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cuz.my.psmm.Messages;
import cuz.my.psmm.Pair;
import cuz.my.psmm.UMessage;
import cuz.my.psmm.UntypedRawMessage;

public class SenderModuleMessage implements SenderModule {

	@Override
	public <T> Parcel<T> parcel(List<Pair<T>> dataPairs, Parcel<T> receivedParcel) {
		// TODO Auto-generated method stub
		UntypedRawMessage newRawMessage;
		int odds = ThreadLocalRandom.current().nextInt(100);
		// 30% possibility to new
		if (odds < 30) {
			newRawMessage = Messages.create();
		} else {
			newRawMessage = receivedParcel.getMessage();
		} // prepare raw message

		for (Pair<T> pair : dataPairs) {
			T value = pair.getValue();
			if (value instanceof Integer) {
				newRawMessage.set(pair.getKey(), (int) pair.getValue());
			} else if (value instanceof Double) {
				newRawMessage.set(pair.getKey(), (double) pair.getValue());
			} else {
				newRawMessage.set(pair.getKey(), (String) pair.getValue());
			}
		}
		UMessage newMessage = newRawMessage.cook(); // set and cook raw message

		return new ParcelVerification<>(newMessage, dataPairs);
	}

}
