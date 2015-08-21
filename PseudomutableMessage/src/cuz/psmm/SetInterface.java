package cuz.psmm;

interface SetInterface {
	RawMessage set(String valueName,String datum) ;
	RawMessage set(String valueName,int datum) ;
	RawMessage set(String valueName,short datum) ;
	RawMessage set(String valueName,long datum) ;
	RawMessage set(String valueName,byte datum) ;
	RawMessage set(String valueName,float datum) ;
	RawMessage set(String valueName,double datum) ;
	RawMessage setObject(String valueName,Object object) ;
}
