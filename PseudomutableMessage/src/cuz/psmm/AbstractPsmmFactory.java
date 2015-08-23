package cuz.psmm;

public abstract class  AbstractPsmmFactory<T,V> implements PsmmFactory<T> {

	

	protected V data;
	protected Message<T> messageBeingWrapped;
	

	abstract protected V designateData();
	@Override
	public PsmmFactory<T> wrap(Message<T> message)  {
		messageBeingWrapped=message;
		data=designateData();
		return this;
	}

	@Override
	public RawMessage<T> set(T datum) {
		// TODO Auto-generated method stub
		return set(null,datum);
	}
	abstract protected Message<T> createMessage(Message<T> messageBeingModified,V data);
	
	@Override
	public Message<T> done()  {
		// TODO Auto-generated method stub

		Message<T> newMessage=this.createMessage(messageBeingWrapped, data);
		data=null;
		messageBeingWrapped=null;

		return newMessage;
	}

	
}
