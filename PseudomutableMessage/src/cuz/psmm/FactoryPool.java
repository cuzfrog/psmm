package cuz.psmm;

import cuz.psmm.Messages.Type;

interface FactoryPool {

	// Functionalities:
	PsmmFactory seekFactory(Type type);

}