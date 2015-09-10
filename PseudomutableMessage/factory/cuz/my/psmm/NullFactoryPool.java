package cuz.my.psmm;

final class NullFactoryPool extends AbstractFactoryPool {

	NullFactoryPool(PsmmConfiguration config) {
		super(config);
	}

	@Override
	protected PsmmFactory createOrFetch() {
		return new PsmmFactoryImpl();
	}

}
