package com.github.cuzfrog.psmm.test.actors;

import java.util.List;

import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.TBuilder;
import com.github.cuzfrog.psmm.UBuilder;

interface Parcel<T> {

	/**
	 * @return the message's raw message
	 */
	UBuilder getMessage();
	
	TBuilder<String,T> getTypedMessage();

	List<Pair<T>> getData();

	boolean verify();

}