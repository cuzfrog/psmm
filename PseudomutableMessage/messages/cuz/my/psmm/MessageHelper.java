package cuz.my.psmm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cuz.my.psmm.PsmmFactory;
import cuz.my.psmm.data.Data;

final class MessageHelper {

	private static Map<byte[], TypedMessage<?>> messagePool = new ConcurrentHashMap<>();

	private MessageHelper() {
	}

	static <T> AbstractRawMessage<T> fetch(Messages.Type type,
			TypedMessage<T> messageBeingWrapped) {
		return new RawMessageImpl<>(PsmmFactory.seekFactory(type),
				messageBeingWrapped);
	}

	@SuppressWarnings("unchecked")
	public static <T> TypedMessage<T> seekMessage(byte[] signature) {
		return (TypedMessage<T>) messagePool.get(signature);
	}

	public static void putMessage(byte[] signature, TypedMessage<?> message) {
		messagePool.put(signature, message);
	}

	public static <T> TypedMessage<T> getRootMessage() {
		return RootMessage.getInstance();
	}

	public static <T> TypedMessage<T> getConcretMessage(Messages.Type type,
			TypedMessage<T> messageBeingWrapped, Data data, byte[] signature) {

		TypedMessage<T> message = new CachedMessage<>(type, messageBeingWrapped,
				data, signature);
		putMessage(signature, message);
		return message;

	}

	public static <T> TypedMessage<T> getConcretMessage(Messages.Type type,
			TypedMessage<T> messageBeingWrapped, Data data) {
		return new UncachedMessage<>(type, messageBeingWrapped, data);
	}
}
