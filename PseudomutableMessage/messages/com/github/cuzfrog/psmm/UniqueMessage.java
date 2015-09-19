package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;

final class UniqueMessage<K,T> extends AbstractMessage<K,T> {

	private static final long serialVersionUID = -5158983613061815925L;

	public UniqueMessage(Messages.Style type,Message<K,T> parent, Data data) {
		super(type, parent, data);
	}

	
}
