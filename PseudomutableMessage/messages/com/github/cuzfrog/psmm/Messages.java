package com.github.cuzfrog.psmm;

import java.util.HashMap;
import java.util.Map;

import com.github.cuzfrog.psmm.PsmmSystem;

/**
 * An abstract API class that has message creation methods and the definition of
 * message styles.
 * <p>
 * There are several message interfaces<br>
 * {@link UMessage} only accept primitives' boxes and Strings, which ensures its
 * data's immutability. <br>
 * {@link TMessage} gives more flexibility, but when you set parameter type to
 * your custom object, you have to be very careful designing your message data,
 * since TypedMessage doesn't check the mutability of the datum you set in.
 * 
 * @author Cause Chung
 *
 */
public abstract class Messages implements MessageCommonInterface {

	private static final long serialVersionUID = 1L;

	//prohibit inheritance
	private Messages() {
		throw new AssertionError();
	}

	/**
	 * Message styles indicating the structures of messages. See document for
	 * details.
	 * 
	 * @author Cause Chung
	 *
	 */
	public enum Style {
		LINKED_MAP, FLAT_MAP, VALUE_LINKED_MAP, VALUE_FLAT_MAP;

		private String name;
		private boolean isValue;
		private static final Map<String,Style> stringToStyleMap=new HashMap<>();
		static{
			for(Style style:Style.values()){
			stringToStyleMap.put(generateName(style), style);
			}
		}
		private Style() {
			this.name=generateName(this);
			this.isValue=this.toString().contains("VALUE");
		}

		private static String generateName(Style style) {
			StringBuilder nameBuilder = new StringBuilder();
			String originalName = style.toString();
			if (originalName.contains("VALUE")) {
				nameBuilder.append("Value");
			} else {
				nameBuilder.append("Unique");
			} // creation attribute

			if (originalName.contains("LINKED")) {
				nameBuilder.append("Linked");
			} else if (originalName.contains("FLAT")) {
				nameBuilder.append("Flat");
			} // structure attribute

			if (originalName.contains("MAP")) {
				nameBuilder.append("Map");
			} // data attribute

			return nameBuilder.toString();
		}

		String getName() {
			return name;
		}
		
		boolean isValue() {
			return isValue;
		}
		
		static Style fromString(String name){
			return stringToStyleMap.get(name);
		}
	}

	/**
	 * Create a new {@link UntypedRawMessage}.
	 * <p>
	 * There are several {@link Messages.Style}s of messages you can choose for
	 * different scenarios.
	 * 
	 * @param messageType
	 *            instructing factory how to generate this message.
	 * @return UntypedRawMessage.
	 */
	public static UntypedRawMessage create(Messages.Style messageType) {
		Message<Object> rootMessage = RootMessage.getInstance();
		AbstractRawMessage<Object> rawMessage = PsmmSystem.fetchRaw(messageType, rootMessage);
		return rawMessage;
	}

	/**
	 * Create a new {@link UntypedRawMessage} .
	 * <p>
	 * This is a helper method for {@link #create(Style)} with the default
	 * message type of {@link Style#LINKED_MAP}.
	 * 
	 * @return RawMessage.
	 */
	public static UntypedRawMessage create() {
		return create(Style.LINKED_MAP);
	}

	/**
	 * Create a new {@link TypedRawMessage} with class parameter type specified.
	 * <p>
	 * There are several {@link Messages.Style}s of message you can choose for
	 * different scenarios.
	 * 
	 * 
	 * @param messageType
	 *            instructing factory how to generate this message.
	 * @param c
	 *            type parameter
	 * @param <T>
	 *            TMessage's type.
	 * @return RawMessage.
	 */
	public static <T> TypedRawMessage<T> create(Messages.Style messageType, Class<T> c) {

		Message<T> rootMessage = RootMessage.getInstance();
		AbstractRawMessage<T> rawMessage = PsmmSystem.fetchRaw(messageType, rootMessage);
		return rawMessage;
	}

	/**
	 * Create a new {@link TypedRawMessage} with class parameter type specified.
	 * <p>
	 * This is a helper method with the default message type of
	 * {@link Style#LINKED_MAP}.
	 * 
	 * @param c
	 *            type parameter
	 * @param <T>
	 *            TMessage's type.
	 * @return RawMessage.
	 */
	public static <T> TypedRawMessage<T> create(Class<T> c) {

		return create(Style.LINKED_MAP, c);
	}
}
