package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;

import cuz.my.psmm.Pair;
import cuz.my.psmm.TMessage;
import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UntypedRawMessage;

public class ParcelTypedVerification<T> implements Parcel<T> {
	private final TMessage<T> message;
	private final List<Pair<T>> expectedData;

	ParcelTypedVerification(TMessage<T> message, List<Pair<T>> expectedData) {
		super();
		this.message = message;
		this.expectedData = new ArrayList<>(expectedData);
	}


	@Override
	public UntypedRawMessage getMessage() {
		return null;
	}

	@Override
	public TypedRawMessage<T> getTypedMessage() {
		// TODO Auto-generated method stub
		return message.raw();
	}

	@Override
	public List<Pair<T>> getData() {
		return new ArrayList<>(expectedData);
	}


	@Override
	public boolean verify() {
		for (Pair<T> pair : expectedData) {
			if (!message.get(pair.getKey()).equals(pair.getValue())) {
				return false;
			}
		}
		return true;
	}
}
