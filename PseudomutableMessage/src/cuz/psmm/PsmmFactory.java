package cuz.psmm;


public interface PsmmFactory extends SetInterface{
	
	PsmmFactory wrap(Message message) ;
	
	Message done() ;
	
	
	static PsmmFactory newCommonPsmmFactory(){
		return new CommonPsmmFactory();
	}
	
	static PsmmFactory seekFactory(Message.Type type,Message messageBeingWrapped){
		//DEBUG:
		PsmmFactory psmmFactory=new CommonPsmmFactory();
		
		
		psmmFactory.wrap(messageBeingWrapped);
		return psmmFactory;
		
	}
}
