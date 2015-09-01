package cuz.my.psmm.test.actors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cuz.my.psmm.Pair;

public class SenderModuleControl implements SenderModule {

	@Override
	public <T> Parcel<T> parcel(List<Pair<T>> data, Parcel<T> receivedParcel) {
		// TODO Auto-generated method stub
		Map<String, T> map = new HashMap<>();
		for (Pair<T> pair : data) {
			map.put(pair.getKey(), pair.getValue());
		}

		return new ParcelControl<>(map, data);
	}

}
