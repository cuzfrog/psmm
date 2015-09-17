package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;

final class UniqueMessage<T> extends AbstractMessage<T> {

	private static final long serialVersionUID = -5158983613061815925L;

	public UniqueMessage(Messages.Style type,Message<T> parent, Data data) {
		super(type, parent, data);
	}

	
}
