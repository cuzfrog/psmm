package cuz.psmm.factory.modules;

import cuz.psmm.exceptions.PsmmException;
import cuz.psmm.factory.datastructure.DataStructure;
import cuz.psmm.message.Message;
import cuz.psmm.message.Message.Type;
import cuz.psmm.message.MessageHelper;

final class DecorativeCachedModule extends Module {

	private final Module moduleDecorated;

	DecorativeCachedModule(Module moduleDecorated) {
		super();
		this.moduleDecorated = moduleDecorated;
	}


	@Override
	public <T> Message<T> createMessage(Message<T> messageBeingWrapped,
			DataStructure data) throws PsmmException {
		// TODO Auto-generated method stub
		byte[] signature = MessageHelper.calculateSignature(
				moduleDecorated.getType(), messageBeingWrapped, data);
		Message<T> message;
		if((message=MessageHelper.seekMessage(signature))==null){
			message=moduleDecorated.createMessage(messageBeingWrapped, data);
			MessageHelper.putMessage(signature, message);
		}
		return message;
	}

	@Override
	public DataStructure designateData() {
		// TODO Auto-generated method stub
		return moduleDecorated.designateData();
	}

	@Override
	public Message.Type getType() {
		// TODO Auto-generated method stub
		return moduleDecorated.getType();
	}

}
