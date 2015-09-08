package cuz.my.psmm;

//assume Factory is run by single thread.
@NotThreadSafe
final class PsmmFactoryImpl implements PsmmFactory {

	private Data data;
	private Module module;
	private Messages.Style type;
	/**
	 * Raw message binded with this factory.
	 */
	private final AbstractRawMessage<?> rawMessageRefr=new RawMessageImpl<>(this);

	@Override
	public <T> MessageAdaptorInterface<T> commit(MessageAdaptorInterface<T> messageBeingWrapped) {
		// TODO Auto-generated method stub

		MessageAdaptorInterface<T> newMessage = module.createMessage(type, messageBeingWrapped, data);

		// once a new message has been created, delete this.data reference
		// for safety.
		data = null;

		return newMessage;

	}

	@Override
	public <T> void set(String key, T datum) {
		// TODO Auto-generated method stub
		data.set(key, datum);
	}

	@Override
	public void assemble(Module module, Messages.Style type) {
		this.type = type;
		module.setup(this);
	}

	// module functions:
	@Override
	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public void setModule(Module module) {
		this.module = module;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> AbstractRawMessage<T> getRawMessage() {
		// TODO Auto-generated method stub
		return (AbstractRawMessage<T>) rawMessageRefr;
	}
}
