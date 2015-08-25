package cuz.psmm.factoryModules;

import java.util.HashMap;
import java.util.Map;

import cuz.psmm.Messages;
import cuz.psmm.Messages.Type;
import cuz.psmm.accessoaries.StateLess;

@StateLess
public final class Modules {

	private Modules(){}
	
	public static Map<Messages.Type,Module> createModuleMap(){
		Map<Messages.Type,Module> moduleList=new HashMap<>();
		
		moduleList.put(Type.LINKED_MAP, new LinkedMapModule());
		moduleList.put(Type.FLAT_MAP, new FlatMapModule());
		
		return moduleList;
	}
}
