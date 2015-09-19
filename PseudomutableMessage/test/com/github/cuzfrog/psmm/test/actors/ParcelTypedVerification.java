package com.github.cuzfrog.psmm.test.actors;

import java.util.ArrayList;
import java.util.List;

import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.TMessage;
import com.github.cuzfrog.psmm.TBuilder;
import com.github.cuzfrog.psmm.UBuilder;

class ParcelTypedVerification<T> implements Parcel<T> {
	private final TMessage<String,T> message;
	private final List<Pair<T>> expectedData;

	ParcelTypedVerification(TMessage<String,T> message, List<Pair<T>> expectedData) {
		super();
		this.message = message;
		this.expectedData = new ArrayList<>(expectedData);
	}


	@Override
	public UBuilder getMessage() {
		return null;
	}

	@Override
	public TBuilder<String,T> getTypedMessage() {
		// TODO Auto-generated method stub
		return message.builder();
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
