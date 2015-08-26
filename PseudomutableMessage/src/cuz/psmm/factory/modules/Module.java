package cuz.psmm.factory.modules;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.exceptions.PsmmException;
import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.datastructure.DataStructure;
import cuz.psmm.message.Message;
import cuz.psmm.message.Message.Type;


public abstract class Module {
	public abstract <T> Message<T> createMessage(Message<T> messageBeingWrapped,
			DataStructure data) throws PsmmException;

	public abstract DataStructure designateData();

	public abstract Message.Type getType();
	
	public static Map<Message.Type,Module> createModuleMap(){
		Map<Message.Type,Module> moduleList=new HashMap<>();
		
		Module linkMapModule=new LinkedMapModule();
		moduleList.put(Message.Type.LINKED_MAP, linkMapModule);
		
		Module flatMapModule=new FlatMapModule();
		moduleList.put(Message.Type.FLAT_MAP, flatMapModule);
		
		moduleList.put(Message.Type.CACHED_LINKED_MAP, new DecorativeCachedModule(linkMapModule));
		moduleList.put(Message.Type.CACHED_FLAT_MAP, new DecorativeCachedModule(flatMapModule));
		
		return moduleList;
	}
	
	//public abstract void setup(PsmmFactory psmmFactory);
}
