package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;

import cuz.my.psmm.Pair;
import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UMessage;
import cuz.my.psmm.UntypedRawMessage;


/**
 * Immutable when message inside is immutable.
 * @author cuzfrog
 *
 */
public  class ParcelVerification<T> implements Parcel<T> {
	private final UMessage message;
	private final List<Pair<T>> expectedData;

	ParcelVerification(UMessage message, List<Pair<T>> expectedData) {
		super();
		this.message = message;
		this.expectedData =new ArrayList<>( expectedData);
	}

	@Override
	public UntypedRawMessage getMessage() {
		return message.raw();
	}
	
	@Override
	public List<Pair<T>> getData(){
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

	@Override
	public TypedRawMessage<T> getTypedMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
