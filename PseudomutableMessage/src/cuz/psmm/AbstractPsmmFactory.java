package cuz.psmm;

public abstract class AbstractPsmmFactory<T, V> implements PsmmFactory<T> {

	protected V data;
	private Message<T> messageBeingWrapped;


	abstract protected V designateData();

	@Override
	public PsmmFactory<T> wrap(Message<T> message) {
		data = designateData();
		messageBeingWrapped = message;
		return this;
	}

	abstract protected Message<T> createMessage(Message<T> messageBeingModified, V data);

	@Override
	public Message<T> cook() {
		// TODO Auto-generated method stub


			Message<T> newMessage = this.createMessage(messageBeingWrapped, data);
			data = null;
			messageBeingWrapped = null;
			return newMessage;

	}
}
