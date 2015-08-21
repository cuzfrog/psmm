package cuz.psmm;


interface PsmmFactory extends SetInterface{
	
	PsmmFactory wrap(Message message) ;
	
	Message done() ;
	
	
	static PsmmFactory newCommonPsmmFactory(){
		return new CommonPsmmFactory();
	}
	
	static RawMessage seekFactory(Message.Type type,Message messageBeingWrapped){
		//DEBUG:
		RawMessage psmmFactory=new CommonPsmmFactory();
		
		
		psmmFactory.wrap(messageBeingWrapped);
		return psmmFactory;
		
	}
}
