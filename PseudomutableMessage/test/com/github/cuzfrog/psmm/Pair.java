package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Immutable;

@Immutable
public class Pair<T> {

	String key;
	T value;

	public String getKey() {
		return key;
	}

	public T getValue() {
		return value;
	}

	public Pair(String key, T value) {
		super();
		this.key = key;
		this.value = value;
	}
}