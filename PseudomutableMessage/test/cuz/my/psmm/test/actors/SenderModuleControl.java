package cuz.my.psmm.test.actors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuz.my.psmm.MyAbstractTest.Pair;

public class SenderModuleControl implements SenderModule {

	@Override
	public Parcel parcel(List<Pair> data, Parcel receivedParcel) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<>();
		for (Pair pair : data) {
			map.put(pair.getKey(), pair.getValue());
		}

		return new ParcelControl(map, data);
	}

}
