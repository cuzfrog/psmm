package com.github.cuzfrog.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;

import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.TBuilder;
import com.github.cuzfrog.psmm.UMessage;
import com.github.cuzfrog.psmm.UBuilder;


/**
 * Immutable when message inside is immutable.
 * @author Cause Chung
 *
 */
class ParcelVerification<T> implements Parcel<T> {
	private final UMessage message;
	private final List<Pair<T>> expectedData;

	ParcelVerification(UMessage message, List<Pair<T>> expectedData) {
		super();
		this.message = message;
		this.expectedData =new ArrayList<>( expectedData);
	}

	@Override
	public UBuilder getMessage() {
		return message.builder();
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
	public TBuilder<String,T> getTypedMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
