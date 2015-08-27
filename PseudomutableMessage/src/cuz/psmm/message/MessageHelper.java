package cuz.psmm.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.data.Data;

public final class MessageHelper {

	private static Map<byte[], Message<?>> messagePool = new ConcurrentHashMap<>();

	private MessageHelper() {
	}

	static <T> RawMessage<T> fetch(Message.Type type,
			Message<T> messageBeingWrapped) {
		return new RawMessageImpl<>(PsmmFactory.seekFactory(type),
				messageBeingWrapped);
	}

	@SuppressWarnings("unchecked")
	public static <T> Message<T> seekMessage(byte[] signature) {
		return (Message<T>) messagePool.get(signature);
	}

	public static void putMessage(byte[] signature, Message<?> message) {
		messagePool.put(signature, message);
	}

	public static <T> Message<T> getRootMessage() {
		return RootMessage.getInstance();
	}

	public static <T> Message<T> getConcretMessage(Message.Type type,
			Message<T> messageBeingWrapped, Data data, byte[] signature) {

		Message<T> message = new CachedMessage<>(type, messageBeingWrapped,
				data, signature);
		putMessage(signature, message);
		return message;

	}

	public static <T> Message<T> getConcretMessage(Message.Type type,
			Message<T> messageBeingWrapped, Data data) {
		return new UncachedMessage<>(type, messageBeingWrapped, data);
	}
}
