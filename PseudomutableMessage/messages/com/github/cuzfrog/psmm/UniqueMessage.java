package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;

final class UniqueMessage<K,T> extends AbstractMessage<K,T> {

	public UniqueMessage(Messages.Style type,Message<K,T> parent, Data data) {
		super(type, parent, data);
	}

	
}
