package cuz.psmm.impl;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.AbstractMessage;
import cuz.psmm.Messages;
import cuz.psmm.RootMessage;

public class FlatPsmm<T> extends AbstractMessage<T, Map<String,T>> {

	@SuppressWarnings("unchecked")
	protected FlatPsmm(Map<String, T> data) {
		
		super(Messages.Type.FLAT_MAP, RootMessage.getInstance(), data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public T get(String key) {
		// TODO Auto-generated method stub
		return data.get(key);
	}

	@Override
	public Map<String, T> getAll() {
		// TODO Auto-generated method stub
		Map<String,T> result=new HashMap<>();
		result.putAll(data);
		return result;
	}

}
