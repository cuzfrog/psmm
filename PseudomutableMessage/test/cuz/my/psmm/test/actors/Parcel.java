package cuz.my.psmm.test.actors;

import java.util.List;

import cuz.my.psmm.MyAbstractTest.Pair;
import cuz.my.psmm.TypedRawMessage;
import cuz.my.psmm.UntypedRawMessage;

public interface Parcel {

	/**
	 * @return the message's raw message
	 */
	UntypedRawMessage getMessage();
	
	<T> TypedRawMessage<T> getTypedMessage();

	List<Pair> getData();

	boolean verify();

}