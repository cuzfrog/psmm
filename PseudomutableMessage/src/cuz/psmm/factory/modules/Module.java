package cuz.psmm.factory.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cuz.psmm.exceptions.PsmmUnsupportedOperationException;
import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.data.Data;
import cuz.psmm.message.Message;

public abstract class Module {

	protected final Module collaberativeModule;
	protected final String name;

	public Module(Module collaberativeModule, String name) {
		this.collaberativeModule = collaberativeModule;
		this.name=collaberativeModule.getName().concat(name);
	}

	public <T> Message<T> createMessage(Message.Type type, Message<T> messageBeingWrapped, Data data) {
		if(collaberativeModule!=null){
			return collaberativeModule.createMessage(type, messageBeingWrapped, data);
		}
		throw new PsmmUnsupportedOperationException();
	}

	public void setup(PsmmFactory psmmFactory) {
		throw new PsmmUnsupportedOperationException();
	}
	
	public String getName(){
		return this.name;
	}

	public static Map<Message.Type, Module> createModuleMap() {
		Map<String, Module> moduleList = new HashMap<>();

		Module cached=new CreationModuleCached();
		Module uncached=new CreationModuleUncached();
		
		List<Module> creations=new LinkedList<>();
		creations.add(cached);
		creations.add(uncached);
		
		
		Module linkedCached = new StructureModuleLinked(cached);
		Module linkedUncached = new StructureModuleLinked(uncached);
		Module flatCached = new StructureModuleFlat(cached);
		Module flatUncached = new StructureModuleFlat(uncached);
		
		Module mapLinkedCached=new DataModuleMap(linkedCached);
		
		moduleList.put(Message.Type.LINKED_MAP, linkMapModule);

		
		moduleList.put(Message.Type.FLAT_MAP, flatMapModule);

		moduleList.put(Message.Type.CACHED_LINKED_MAP, new CreationModuleCached(linkMapModule));
		moduleList.put(Message.Type.CACHED_FLAT_MAP, new CreationModuleCached(flatMapModule));

		return moduleList;
	}

}
