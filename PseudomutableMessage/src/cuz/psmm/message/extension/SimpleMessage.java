package cuz.psmm.message.extension;

import cuz.psmm.Psmm;
import cuz.psmm.message.RawMessage;

public interface SimpleMessage<T> extends Psmm {
	T get();
	RawMessage<T> set(T datum);
}
