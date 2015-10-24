package com.github.cuzfrog.psmm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class MapData extends Data {

	private Map<Object, Object> map = new HashMap<>();

	@Override
	public <T> void set(Object key, T datum) {
		map.put(key, datum);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key) {
		return (T) map.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K,T> Map<K, T> getAll() {
		return (Map<K, T>) map;
	}

	@Deprecated
	@Override
	public byte[] getDataStream() throws IOException {
		return Data.getDataStream(map);
	}

	@Override
	public Data merge(Data data) {
		assert data != null && data.getStructure() == this.getStructure();
		this.map.putAll(data.getAll());
		return this;
	}

	@Override
	public Structure getStructure() {
		return Structure.MAP;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
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
