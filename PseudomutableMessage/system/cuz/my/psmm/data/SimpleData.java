package cuz.my.psmm.data;

import cuz.my.psmm.Checkable;

final class SimpleData extends Data {

	private Object value;

	@Override
	public void set(Object datum) {
		this.value=datum;
	}

	@Override
	public Object get() {
		return value;
	}

	@Override
	public Data merge(Data data) {
		return data;
	}

	@Override
	public Structure getStructure() {
		// TODO Auto-generated method stub
		return Structure.SIMPLE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Checkable)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Checkable signature=(Checkable) obj;
		Data otherData=signature.readData();
		if (getClass() != otherData.getClass()) {
			return false;
		}
		SimpleData other = (SimpleData) otherData;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
