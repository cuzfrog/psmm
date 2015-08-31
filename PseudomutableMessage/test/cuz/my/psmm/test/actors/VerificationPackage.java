package cuz.my.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;

import cuz.my.psmm.MyAbstractTest.Pair;
import cuz.my.psmm.UMessage;


/**
 * Immutable when message inside is immutable.
 * @author cuzfrog
 *
 */
final class VerificationPackage {
	private final UMessage message;
	private final List<Pair> expectedData;

	VerificationPackage(UMessage message, List<Pair> expectedData) {
		super();
		this.message = message;
		this.expectedData =new ArrayList<>( expectedData);
	}

	/**
	 * @return the message
	 */
	UMessage getMessage() {
		return message;
	}
	
	List<Pair> getData(){
		return new ArrayList<>(expectedData);
	}

	boolean verify() {
		for (Pair pair : expectedData) {
			if (!message.get(pair.getKey()).equals(pair.getValue())) {
				return false;
			}
		}
		return true;
	}
}
