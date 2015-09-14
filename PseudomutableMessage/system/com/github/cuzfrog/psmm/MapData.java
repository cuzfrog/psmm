package com.github.cuzfrog.psmm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
	public byte[] getDataStream() throws IOException {
		return Data.getDataStream(map);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MapData other = (MapData) obj;
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
