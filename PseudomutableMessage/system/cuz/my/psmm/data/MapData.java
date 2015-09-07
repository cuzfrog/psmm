package cuz.my.psmm.data;

import java.util.HashMap;
import java.util.Map;

import cuz.my.psmm.Checkable;

final class MapData extends Data {

	private Map<String, Object> map = new HashMap<>();

	@Override
	public <T> void set(String key, T datum) {
		map.put(key, datum);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key) {
		return (T) map.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Map<String, T> getAll() {
		return (Map<String, T>) map;
	}

	@Override
	public Data merge(Data data) {
		//if (data != null && data.getStructure() == this.getStructure()) {
			this.map.putAll(data.getAll());
		//}  for performance's sake
		return this;
	}

	@Override
	public Structure getStructure() {
		// TODO Auto-generated method stub
		return Structure.MAP;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {	
		if (obj == null || !(obj instanceof Checkable)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Checkable signature=(Checkable) obj;
		Data otherData=signature.readData();
		if (getClass() != otherData.getClass()) {
			return false;
		}
		MapData other = (MapData) otherData;
		if (map == null) {
			if (other.map != null) {
				return false;
			}
		} else if (!map.equals(other.map)) {
			return false;
		}
		return true;
	}

}
