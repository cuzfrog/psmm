package cuz.my.psmm;

/**
 * Pool for caching PsmmFactory and RawMessage.
 * <p>
 * When creating new messages, we need to use factory objects to encapsulate new
 * data for changing.This FactoryPool provides a mechanism to cache those
 * objects to reduce object creating overhead. For specific algorithm, see each
 * FactoryPool implementation.
 * 
 * @author cuzfrog
 * @see MapFactoryPool
 */
@ThreadSafe
interface FactoryPool {

	// Functionalities:
	PsmmFactory seekFactory(Messages.Type type);

}