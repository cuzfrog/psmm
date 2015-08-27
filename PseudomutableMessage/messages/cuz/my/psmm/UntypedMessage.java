package cuz.my.psmm;

import cuz.my.psmm.Psmm;

public interface UntypedMessage extends Psmm {

	public abstract Object get(String key);
	public abstract UntypedRawMessage set(String key,int value);
	public abstract UntypedRawMessage set(String key,short value);
	public abstract UntypedRawMessage set(String key,long value);
	public abstract UntypedRawMessage set(String key,boolean value);
	public abstract UntypedRawMessage set(String key,float value);
	public abstract UntypedRawMessage set(String key,double value);
	public abstract UntypedRawMessage set(String key,char value);
	public abstract UntypedRawMessage set(String key,byte value);
	public abstract UntypedRawMessage set(String key,String value);
	
}
