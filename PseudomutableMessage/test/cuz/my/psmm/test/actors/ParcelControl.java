package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UntypedRawMessage;
import cuz.my.psmm.MyAbstractTest.Pair;

public final class ParcelControl implements Parcel {
	private final Map<String,Integer> message;
	private final List<Pair> expectedData;

	ParcelControl(Map<String,Integer> message, List<Pair> expectedData) {
		super();
		this.message = new HashMap<>(message);
		this.expectedData =new ArrayList<>( expectedData);
	}

	@Override
	public UntypedRawMessage getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair> getData() {
		// TODO Auto-generated method stub
		return new ArrayList<>(expectedData);
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		for (Pair pair : expectedData) {
			if (!message.get(pair.getKey()).equals(pair.getValue())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public <T> TypedRawMessage<T> getTypedMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
