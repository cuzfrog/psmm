package com.github.cuzfrog.psmm.test.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.TBuilder;
import com.github.cuzfrog.psmm.UBuilder;

final class ParcelControl<T> implements Parcel<T> {
	private final Map<String,T> message;
	private final List<Pair<T>> expectedData;

	ParcelControl(Map<String,T> message, List<Pair<T>> expectedData) {
		super();
		this.message = new HashMap<>(message);
		this.expectedData =new ArrayList<>( expectedData);
	}

	@Override
	public UBuilder getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<T>> getData() {
		// TODO Auto-generated method stub
		return new ArrayList<>(expectedData);
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
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
