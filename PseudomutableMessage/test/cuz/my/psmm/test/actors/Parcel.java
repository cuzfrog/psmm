package cuz.my.psmm.test.actors;

import java.util.List;

import cuz.my.psmm.Pair;
import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UntypedRawMessage;

public interface Parcel<T> {

	/**
	 * @return the message's raw message
	 */
	UntypedRawMessage getMessage();
	
	TypedRawMessage<T> getTypedMessage();

	List<Pair<T>> getData();

	boolean verify();

}