package cuz.my.psmm;

final class UncachedMessage<T> extends AbstractMessage<T> {


	private static final long serialVersionUID = 1L;

	public UncachedMessage(Messages.Style type,MessageAdaptorInterface<T> parent, Data data) {
		super(type, parent, data);
	}

	
}
