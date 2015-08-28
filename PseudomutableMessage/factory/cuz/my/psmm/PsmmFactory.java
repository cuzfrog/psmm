package cuz.my.psmm;

import cuz.my.psmm.accessories.NotThreadSafe;
import cuz.my.psmm.data.Data;

@NotThreadSafe
interface PsmmFactory {

	public abstract <T> PsmmFactory assemble(Module module, Messages.Type type);

	public abstract <T> TMessage<T> commit(TMessage<T> messageBeingWrapped);

	public abstract <T> void set(String key, T datum);

	public abstract void setData(Data data);

	public abstract void setModule(Module module);

}
