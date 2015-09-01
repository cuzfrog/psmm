package cuz.my.psmm.test.actors.threadinterface;

import cuz.my.psmm.Messages.Style;
import cuz.my.psmm.Pair;
import cuz.my.psmm.test.actors.SenderModule;

public interface SharedReadOnlyData {
	public Style randomType();

//	public Pair<Integer> randomIntegerPair();
//	public Pair<Double> randomDoublePair();
//	public Pair<String> randomStringPair();
	
	public Pair<?> randomPair();
	
	public String randomName();
	
	public SenderModule getModule();
}
