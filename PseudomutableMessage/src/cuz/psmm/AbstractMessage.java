package cuz.psmm;

public abstract class AbstractMessage<T,V> implements Message<T> {



	protected final Message<T> parent;
	protected final V data;
	protected final Integer depth;
	protected final Message.Type type;



	protected AbstractMessage(Message.Type type, Message<T> parent, V data) {
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
		return null;
	}

	// ------------factory behaviors:
	private PsmmFactory<T> getFactory(Message.Type type) {

		return PsmmFactory.seekFactory(type, this);
	}
	@Override
	public RawMessage<T> set(String key, T datum) {
		// TODO Auto-generated method stub
		return getFactory(this.type).set(key, datum);
	}

	@Override
	public RawMessage<T> set(T datum) {
		return set(null,datum);
		
	}

}
