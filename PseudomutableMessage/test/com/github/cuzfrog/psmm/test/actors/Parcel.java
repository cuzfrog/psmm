package com.github.cuzfrog.psmm.test.actors;

import java.util.List;

import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.TypedRawMessage;
import com.github.cuzfrog.psmm.UntypedRawMessage;

interface Parcel<T> {

	/**
	 * @return the message's raw message
	 */
	UntypedRawMessage getMessage();
	
	TypedRawMessage<T> getTypedMessage();

	List<Pair<T>> getData();

	boolean verify();

}