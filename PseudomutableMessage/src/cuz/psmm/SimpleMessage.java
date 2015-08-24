package cuz.psmm;

public interface SimpleMessage<T> extends Psmm {
	T get();
	RawMessage<T> set(T datum);
}
