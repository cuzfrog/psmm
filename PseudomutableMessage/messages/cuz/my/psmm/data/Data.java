package cuz.my.psmm.data;

import java.io.IOException;
import java.util.Map;

public abstract class Data {
	public <T> void set(String key, T datum) {
	}

	public void set(Object datum) {
	}

	public <T> T get(String key) {
		return null;
	}

	public Object get() {
		return null;
	}

	public <T> Map<String, T> getAll() {
		return null;
	}

	public byte[] getSignature() throws IOException {
		return null;
	}

	public static Data newData(Data.Type type) {
		switch (type) {
		case MAP:
			return new MapData();
		default:
			break;
		}
		return null;
	}

	public static enum Type {
		MAP
	}
}
