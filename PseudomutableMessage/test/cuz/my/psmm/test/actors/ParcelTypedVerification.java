package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;

import cuz.my.psmm.MyAbstractTest.Pair;
import cuz.my.psmm.TMessage;
import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UntypedRawMessage;

public class ParcelTypedVerification<T> implements Parcel {
	private final TMessage<T> message;
	private final List<Pair> expectedData;

	ParcelTypedVerification(TMessage<T> message, List<Pair> expectedData) {
		super();
		this.message = message;
		this.expectedData = new ArrayList<>(expectedData);
	}


	@Override
	public UntypedRawMessage getMessage() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TypedRawMessage<T> getTypedMessage() {
		// TODO Auto-generated method stub
		return message.raw();
	}

	@Override
	public List<Pair> getData() {
		return new ArrayList<>(expectedData);
	}


	@Override
	public boolean verify() {
		for (Pair pair : expectedData) {
			if (!message.get(pair.getKey()).equals(pair.getValue())) {
				return false;
			}
		}
		return true;
	}
}
