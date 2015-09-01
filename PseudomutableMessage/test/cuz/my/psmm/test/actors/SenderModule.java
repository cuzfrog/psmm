package cuz.my.psmm.test.actors;

import java.util.List;

import cuz.my.psmm.Pair;

public interface SenderModule {
	<T> Parcel<T> parcel(List<Pair<T>> data,Parcel<T> receivedParcel);
}
