package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Messages.Style;

final class ValueMessage<K,T> extends AbstractMessage<K,T> {

	private volatile int hashCode;

	protected ValueMessage(Style type, Message<K,T> parent, Data data) {
		super(type, parent, data);
		// TODO Auto-generated constructor stub
	}

	// ----------------------------hashcode and equals:
	@Override
	public int hashCode() {
		if (hashCode == 0) {
			final int prime = 31;
			int result = 17;
			Data mergedData = readData();
			result = prime * result + ((mergedData == null) ? 0 : mergedData.hashCode());
			// read all data including those stored in parents as a whole
			hashCode = result;
			return result;
		} else {
			return hashCode;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Message)) {
			return false;
		}

		Message<?,?> other = (Message<?,?>) obj;
		Data otherData = other.readData();
		if (otherData == null) {
			return false;
		}
		Data thisData = this.readData();
		if (!thisData.equals(otherData)) {
			return false;
		}
		return true;
	}

}
