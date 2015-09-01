package cuz.my.psmm;

import cuz.my.psmm.Messages.Style;
import cuz.my.psmm.MyAbstractTest.Pair;
import cuz.my.psmm.test.actors.SenderModule;

public interface SharedReadOnlyData {
	public Style randomType();

	public Pair randomPair();
	
	public String randomName();
	
	public SenderModule getModule();
}
