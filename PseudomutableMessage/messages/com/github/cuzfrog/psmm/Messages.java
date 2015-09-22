package com.github.cuzfrog.psmm;

import java.util.HashMap;
import java.util.Map;

import com.github.cuzfrog.psmm.PsmmSystem;

/**
 * An abstract API class that has builder creation methods and the definition of
 * message styles.
 * <p>
 * There are several message interfaces<br>
 * {@link UMessage} only accept primitives' boxes and Strings, which ensures its
 * data's immutability. <br>
 * {@link TMessage} gives more flexibility, but when you set parameter type to
 * your custom object, you have to be very careful designing your message data,
 * since TypedMessage doesn't check the mutability of the datum you set in.
 * 
 * <p>
 * Throw IllegalStateException when message chain is longer than message's max
 * depth.
 * 
 * @author Cause Chung
 */
public abstract class Messages implements MessageCommonInterface {


	// prohibit inheritance
	private Messages() {
		throw new AssertionError();
	}

	/**
	 * Message styles indicating the structures of messages. See documentation
	 * http://cuzfrog.github.io/psmm for details.
	 * <p>
	 * MAP: message implements map as data structure.
	 * <br>LINKED: new message decorate old message as chain.
	 * <br>FLAT: new message merge old message's data.
	 * <br>VALUE: message equals another when exhibiting same values.
	 * @author Cause Chung
	 */
	public enum Style {
		LINKED_MAP, FLAT_MAP, VALUE_LINKED_MAP, VALUE_FLAT_MAP;

		private String name;
		private boolean isValue;
		private static final Map<String, Style> stringToStyleMap = new HashMap<>();

		static {
			for (Style style : Style.values()) {
				stringToStyleMap.put(generateName(style), style);
			}
		}

		private Style() {
			this.name = generateName(this);
			this.isValue = this.toString().contains("VALUE");
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

		static Style fromString(String name) {
			return stringToStyleMap.get(name);
		}
	}

	/**
	 * Create a new {@link UBuilder}.
	 * <p>
	 * There are several {@link Messages.Style}s of messages you can choose for
	 * different scenarios.
	 * 
	 * @param messageType
	 *            instructing factory how to generate this message.
	 * @return UBuilder.
	 */
	public static UBuilder builder(Messages.Style messageType) {
		Message<Object, Object> rootMessage = RootMessage.getInstance();
		AbstractBuilder<Object, Object> builder = PsmmSystem.fetchBuilder(messageType, rootMessage);
		return builder;
	}

	/**
	 * Create a new {@link UBuilder} .
	 * <p>
	 * This is a helper method for {@link #builder(Style)} with the default
	 * message type of {@link Style#LINKED_MAP}.
	 * 
	 * @return UBuilder.
	 */
	public static UBuilder builder() {
		return builder(Style.LINKED_MAP);
	}

	/**
	 * Create a new {@link TBuilder} with class parameter type specified.
	 * <p>
	 * There are several {@link Messages.Style}s of message you can choose for
	 * different scenarios.
	 * 
	 * 
	 * @param messageStyle
	 *            instructing factory how to generate this message.
	 * @param <K>
	 *            Key's type.
	 * @param <T>
	 *            TMessage's type.
	 * @return TBuilder.
	 */
	public static <K, T> TBuilder<K, T> typedBuilder(Messages.Style messageStyle) {

		Message<K, T> rootMessage = RootMessage.getInstance();
		AbstractBuilder<K, T> builder = PsmmSystem.fetchBuilder(messageStyle, rootMessage);
		return builder;
	}

	/**
	 * Create a new {@link TBuilder} with class parameter type specified.
	 * <p>
	 * This is a helper method with the default message type of
	 * {@link Style#LINKED_MAP}.
	 * 
	 * @param <K>
	 *            Key's type.
	 * @param <T>
	 *            TMessage's type.
	 * @return TBuilder.
	 */
	public static <K, T> TBuilder<K, T> typedBuilder() {

		return typedBuilder(Style.LINKED_MAP);
	}
}
