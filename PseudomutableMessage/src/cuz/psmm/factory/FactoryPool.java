package cuz.psmm.factory;

import cuz.psmm.message.Message;

interface FactoryPool {

	// Functionalities:
	PsmmFactory seekFactory(Message.Type type);

}