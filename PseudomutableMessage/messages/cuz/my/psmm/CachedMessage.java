package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class CachedMessage<T> extends AbstractMessage<T>{

    //speedup hashcode calculation
	private final int hashcode;
	
	public CachedMessage(Messages.Style type, Message<T> parent, Data data) {
		super(type, parent, data);
		hashcode=super.hashCode();
	}

	@Override
	public int hashCode() {
		return hashcode;
	}	
}
