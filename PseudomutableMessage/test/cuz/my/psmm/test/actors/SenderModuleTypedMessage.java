package cuz.my.psmm.test.actors;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cuz.my.psmm.Messages;
import cuz.my.psmm.Pair;
import cuz.my.psmm.TMessage;
import cuz.my.psmm.TypedRawMessage;

class SenderModuleTypedMessage implements SenderModule {

	@Override
	public <T> Parcel<T> parcel(List<Pair<T>> dataPairs, Parcel<T> receivedParcel) {
		// TODO Auto-generated method stub
		TypedRawMessage<T> newRawMessage;
		final Class<T> c = null;
		int odds = ThreadLocalRandom.current().nextInt(100);
		// 30% possibility to new
		if (odds < 30) {
			newRawMessage = Messages.create(c);
		} else {
			newRawMessage = receivedParcel.getTypedMessage();
		} // prepare raw message

		for (Pair<T> pair : dataPairs) {
			newRawMessage.set(pair.getKey(), pair.getValue());
		}
		TMessage<T> newMessage = newRawMessage.cook(); // set and cook raw message

		return new ParcelTypedVerification<>(newMessage, dataPairs);
	}

}
