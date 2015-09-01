package cuz.my.psmm.test.actors;

import java.util.List;

import cuz.my.psmm.MyAbstractTest.Pair;

public interface SenderModule {
	Parcel parcel(List<Pair> data,Parcel receivedParcel);
}
