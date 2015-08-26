package cuz.psmm.factory.datastructure;

import java.io.IOException;

public abstract class DataStructure {
	public abstract <T> void set(String key, T datum);

	public abstract <T> T get(String key);

	public abstract <T> T getAll();
	
	public abstract byte[] getSignature() throws IOException;
	
	public static DataStructure newDataStructure(DataStructure.Type type){
		switch(type){
		case MAP:
			return new MapDataStructure();
		default:
			break;
		}
		return null;
	}
	
	public static enum Type{
		MAP
	}
}
