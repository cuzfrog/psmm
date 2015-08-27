package cuz.psmm.message;

import cuz.psmm.Psmm;

public interface SimpleMessage extends Psmm {
	Object get();
	SimpleMessage set(Object datum);
}
