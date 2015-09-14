package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.PsmmConfiguration;

final class NullFactoryPool extends AbstractFactoryPool {

	NullFactoryPool(PsmmConfiguration config) {
		super(config);
	}

	@Override
	protected PsmmFactory createOrFetch() {
		return new PsmmFactoryImpl();
	}

	@Override
	public int checkAndTrim(int sizeLimit) {
		return 0;
	}
}
