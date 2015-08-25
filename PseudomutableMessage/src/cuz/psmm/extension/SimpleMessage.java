package cuz.psmm.extension;

import cuz.psmm.Psmm;
import cuz.psmm.RawMessage;

public interface SimpleMessage<T> extends Psmm {
	T get();
	RawMessage<T> set(T datum);
}
