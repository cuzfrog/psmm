package cuz.my.psmm.test.actors;

import cuz.my.psmm.Messages.Style;
import cuz.my.psmm.Pair;

interface SharedReadOnlyData {
	public Style randomType();

	public Pair<?> randomPair();

	public String randomName();

	public SenderModule getModule();
}
