package cuz.my.psmm.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

	public byte[] getDataStream() throws IOException {
		return null;
	}

	public static Data newData(Data.Type type) {
		switch (type) {
		case MAP:
			return new MapData();
		case SIMPLE:
			return new SimpleData();
		default:
			break;
		}
		return null;
	}

	public static enum Type {
		MAP, SIMPLE
	}

	public static <T> byte[] getDataStream(Object data) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ObjectOutputStream os = new ObjectOutputStream(outputStream);
		os.writeObject(data);

		return outputStream.toByteArray();
	}
}