package cuz.my.psmm;


public interface UntypedRawMessage {
	UntypedRawMessage set(String key,int value);
	UntypedRawMessage set(String key,short value);
	UntypedRawMessage set(String key,long value);
	UntypedRawMessage set(String key,boolean value);
	UntypedRawMessage set(String key,float value);
	UntypedRawMessage set(String key,double value);
	UntypedRawMessage set(String key,char value);
	UntypedRawMessage set(String key,byte value);
	UntypedRawMessage set(String key,String value);
	UntypedMessage cook();
}
