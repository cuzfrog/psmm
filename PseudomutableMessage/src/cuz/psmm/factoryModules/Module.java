package cuz.psmm.factoryModules;

import cuz.psmm.Message;
import cuz.psmm.accessoaries.StateLess;

@StateLess
public interface Module {
	<T> Message<T> createMessage(Message<T> messageBeingWrapped, DataStructure data);

	DataStructure designateData();
}
