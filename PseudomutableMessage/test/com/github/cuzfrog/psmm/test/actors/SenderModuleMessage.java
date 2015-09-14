package com.github.cuzfrog.psmm.test.actors;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.github.cuzfrog.psmm.Messages;
import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.UMessage;
import com.github.cuzfrog.psmm.UntypedRawMessage;

class SenderModuleMessage implements SenderModule {

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
				newRawMessage.set(pair.getKey(), (Integer) pair.getValue());
			} else if (value instanceof Double) {
				newRawMessage.set(pair.getKey(), (Integer) pair.getValue());
			} else {
				newRawMessage.set(pair.getKey(), (String) pair.getValue());
			}
		}
		UMessage newMessage = newRawMessage.cook(); // set and cook raw message

		return new ParcelVerification<>(newMessage, dataPairs);
	}

}
