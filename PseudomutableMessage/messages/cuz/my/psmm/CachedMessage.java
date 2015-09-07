package cuz.my.psmm;

import cuz.my.psmm.data.Data;

final class CachedMessage<T> extends AbstractMessage<T> {

	// speedup hashcode checking
	private final int hashcode;
	private final Data signature;

	public CachedMessage(Messages.Style type, Message<T> parent, Data data, Data signature) {
		super(type, parent, data);
		hashcode = generateHashCode();
		this.signature=signature;
	}

	private int generateHashCode() {
		final int prime = 31;
		int result = 1;
		Data mergedData = readData();
		result = prime * result
				+ ((mergedData == null) ? 0 : mergedData.hashCode());
		// read all data including those stored in parents as a whole
		return result;
	}
	
	@Override
	public Data readData(){
		return signature;
	}
	
	// ----------------------------hashcode and equals:
	@Override
	public int hashCode() {
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		CachedMessage other = (CachedMessage) obj;
		Data otherData = other.readData();
		Data thisData = this.readData();
		if (otherData == null) {
			return false;
		} else if (!thisData.equals(otherData)) {
			return false;
		}
		return true;
	}
}
