package cuz.my.psmm;

final class FreeMessage<T> extends AbstractMessage<T> {


	private static final long serialVersionUID = 1L;

	public FreeMessage(Messages.Style type,MessageAdaptorInterface<T> parent, Data data) {
		super(type, parent, data);
	}

	
}
