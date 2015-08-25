package cuz.psmm;

import cuz.psmm.accessoaries.NotThreadSafe;
import cuz.psmm.factoryModules.Module;

@NotThreadSafe
interface PsmmFactory{
	<T> PsmmFactory assemble(Module module);
	<T> Message<T> commit(Message<T> messageBeingWrapped);
	<T> void set(String key,T datum);
}
