package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;

import cuz.my.psmm.MyAbstractTest.Pair;
import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UMessage;
import cuz.my.psmm.UntypedRawMessage;


/**
 * Immutable when message inside is immutable.
 * @author cuzfrog
 *
 */
public  class ParcelVerification implements Parcel {
	private final UMessage message;
	private final List<Pair> expectedData;

	ParcelVerification(UMessage message, List<Pair> expectedData) {
		super();
		this.message = message;
		this.expectedData =new ArrayList<>( expectedData);
	}

	/* (non-Javadoc)
	 * @see cuz.my.psmm.test.actors.Parcel#getMessage()
	 */
	@Override
	public UntypedRawMessage getMessage() {
		return message.raw();
	}
	
	/* (non-Javadoc)
	 * @see cuz.my.psmm.test.actors.Parcel#getData()
	 */
	@Override
	public List<Pair> getData(){
		return new ArrayList<>(expectedData);
	}

	/* (non-Javadoc)
	 * @see cuz.my.psmm.test.actors.Parcel#verify()
	 */
	@Override
	public boolean verify() {
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
