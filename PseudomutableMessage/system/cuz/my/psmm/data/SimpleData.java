package cuz.my.psmm.data;

final class SimpleData extends Data {

	private Object value;

	@Override
	public void set(Object datum) {
		// TODO Auto-generated method stub
		this.value=datum;
	}

	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return value;
	}
	
}
