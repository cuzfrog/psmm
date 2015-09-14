package com.github.cuzfrog.psmm.test.actors;

import java.util.List;

import com.github.cuzfrog.psmm.Pair;

interface SenderModule {
	<T> Parcel<T> parcel(List<Pair<T>> data,Parcel<T> receivedParcel);
}
