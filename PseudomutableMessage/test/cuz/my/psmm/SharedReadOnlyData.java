package cuz.my.psmm;

import cuz.my.psmm.Messages.Style;
import cuz.my.psmm.MyAbstractTest.Pair;

public interface SharedReadOnlyData {
	public Style randomType();

	public Pair randomPair();
	
	public String randomName();
}
