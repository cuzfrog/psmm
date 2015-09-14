package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;

final class FreeMessage<T> extends AbstractMessage<T> {


	private static final long serialVersionUID = 1L;

	public FreeMessage(Messages.Style type,Message<T> parent, Data data) {
		super(type, parent, data);
	}

	
}
