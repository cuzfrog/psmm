package cuz.psmm.impl;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.AbstractMessage;
import cuz.psmm.Message;
import cuz.psmm.exceptions.PsmmUnsupportedOperationException;

public class LinkedMessage<T> extends AbstractMessage<T,Map<String, T>> {

	LinkedMessage(Message<T> parent, Map<String, T> data) {
		super(Message.Type.LINKED, parent, data);
		// TODO Auto-generated constructor stub
	}

	@Override
	public T get(String key) {
		// TODO Auto-generated method stub
		T result = null;
		if ((result = data.get(key)) == null) {
			result = parent.get(key);
		}
		return result;
	}

	@Override
	public T get() {
		// TODO Auto-generated method stub
		throw new PsmmUnsupportedOperationException();
	}

	@Override
	public Map<String, T> getAll() {
		// TODO Auto-generated method stub
		Map<String, T> resultMap;
		Map<String, T> parentResult;
		if ((parentResult=parent.getAll()) != null) {
			resultMap= parentResult;
		}else{
			resultMap = new HashMap<>();	
		}
		resultMap.putAll(this.data);
		return resultMap;
	}	
}
