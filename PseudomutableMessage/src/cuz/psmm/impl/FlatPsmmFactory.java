package cuz.psmm.impl;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.AbstractPsmmFactory;
import cuz.psmm.Message;
import cuz.psmm.RawMessage;

public class FlatPsmmFactory<T> extends AbstractPsmmFactory<T, Map<String,T>> {

	@Override
	public RawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		data.put(key, datum);
		return this;
	}

	@Override
	protected Map<String, T> designateData() {
		// TODO Auto-generated method stub
		return new HashMap<String, T>();
	}

	@Override
	protected Message<T> createMessage(Message<T> messageBeingModified,
			Map<String, T> data) {
		Map<String, T> flatData=messageBeingModified.getAll();
		flatData.putAll(data);
		return new FlatPsmm<T>(flatData);
	}

}
