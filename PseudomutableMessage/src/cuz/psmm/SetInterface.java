package cuz.psmm;

interface SetInterface {
	PsmmFactory set(String valueName,String datum) ;
	PsmmFactory set(String valueName,int datum) ;
	PsmmFactory set(String valueName,short datum) ;
	PsmmFactory set(String valueName,long datum) ;
	PsmmFactory set(String valueName,byte datum) ;
	PsmmFactory set(String valueName,float datum) ;
	PsmmFactory set(String valueName,double datum) ;
	PsmmFactory setObject(String valueName,Object object) ;
}
