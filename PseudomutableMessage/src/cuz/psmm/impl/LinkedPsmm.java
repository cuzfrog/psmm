package cuz.psmm.impl;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.AbstractMessage;
import cuz.psmm.Message;
import cuz.psmm.Messages;

public class LinkedPsmm<T> extends AbstractMessage<T,Map<String, T>> {

	LinkedPsmm(Message<T> parent, Map<String, T> data) {
		super(Messages.Type.LINKED_MAP, parent, data);
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
