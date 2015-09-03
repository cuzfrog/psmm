package cuz.my.psmm;

/**
 * An mediate interface between low level class( {@link AbstractMessage},
 * {@link RootMessage}) and high level ones({@link TMessage<T>},
 * {@link UMessage})
 * 
 * @author Cause Chung
 *
 * @param <T>
 */
public interface Message<T> extends TMessage<T>, UMessage {
	AbstractRawMessage<T> raw();
}
