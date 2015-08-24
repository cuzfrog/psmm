package cuz.psmm;


public abstract class AbstractMessage<T,V> implements Message<T> {



	protected final Message<T> parent;
	protected final V data;
	protected final Integer depth;
	protected final Messages.Type type;



	protected AbstractMessage(Messages.Type type, Message<T> parent, V data) {
		super();
		this.parent = parent;
		this.data = data;
		this.depth = parent.depth() + 1;
		this.type = type;

	}

	@Override
	public Integer depth() {
		// TODO Auto-generated method stub
		return depth;
	}
	@Override
	public byte[] getSignature(){
		return new byte[]{Integer.valueOf(0).byteValue()};
	}

	// ------------factory behaviors:
	private PsmmFactory<T> getFactory(Messages.Type type) {

		return PsmmFactory.seekFactory(type, this);
	}
	@Override
	public RawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		return getFactory(this.type).set(key, datum);
	}

}
