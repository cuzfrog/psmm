/**
 * 
 */
package com.github.cuzfrog.psmm;

/**
 * Use ThreadLocal to attach factory reference to every thread.
 * @author Cause Chung
 *
 */
final class ThreadLocalFactoryPool extends AbstractFactoryPool {

	private static final ThreadLocal<PsmmFactory> pool = new ThreadLocal<PsmmFactory>() {
		@Override
		protected PsmmFactory initialValue() {
			return new PsmmFactoryImpl();
		}
	};

	ThreadLocalFactoryPool(PsmmConfiguration config) {
		super(config);
	}

	@Override
	protected PsmmFactory createOrFetch() {
		return pool.get();
	}

}
