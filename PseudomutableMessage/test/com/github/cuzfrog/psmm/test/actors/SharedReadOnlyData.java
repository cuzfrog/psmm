package com.github.cuzfrog.psmm.test.actors;

import com.github.cuzfrog.psmm.Pair;
import com.github.cuzfrog.psmm.Messages.Style;

interface SharedReadOnlyData {
	public Style randomType();

	public Pair<?> randomPair();

	public String randomName();

	public SenderModule getModule();
}
