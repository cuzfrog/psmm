package com.github.cuzfrog.psmm;

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
	
}
