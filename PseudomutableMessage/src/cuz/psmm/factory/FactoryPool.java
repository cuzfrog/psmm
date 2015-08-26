package cuz.psmm.factory;

import cuz.psmm.message.Message;
import cuz.psmm.message.Message.Type;

interface FactoryPool {

	// Functionalities:
	PsmmFactory seekFactory(Message.Type type);

}