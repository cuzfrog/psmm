package cuz.psmm.impl;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.AbstractPsmmFactory;
import cuz.psmm.Message;
import cuz.psmm.RawMessage;

final public class LinkedPsmmFactory<T> extends AbstractPsmmFactory<T,Map<String, T>> {

	@Override
	protected Message<T> createMessage(Message<T> messageBeingWrapped,
			Map<String, T> data) {
		// TODO Auto-generated method stub
		return new LinkedMessage<T>(messageBeingWrapped, data);
	}

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




}
