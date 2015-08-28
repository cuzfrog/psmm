package cuz.my.psmm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cuz.my.psmm.data.Data;
import cuz.my.psmm.exceptions.PsmmUnsupportedOperationException;

abstract class Module {

	private final Module collaberativeModule;
	private final String name;

	protected Module(Module collaberativeModule, String name) {
		this.collaberativeModule = collaberativeModule;
		if (collaberativeModule != null) {
			this.name = collaberativeModule.getName().concat(name);
		} else {
			this.name = name;
		}
	}

	protected <T> TMessage<T> createMessage(Messages.Type type,
			TMessage<T> messageBeingWrapped, Data data) {
		if (collaberativeModule != null) {
			return collaberativeModule.createMessage(type, messageBeingWrapped,
					data);
		}
		throw new PsmmUnsupportedOperationException();
	}

	protected void setup(PsmmFactory psmmFactory) {
		throw new PsmmUnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	public Module getCollaberativeModule() {
		return collaberativeModule;
	}
	
	//static methods:
	static Map<String, Module> createModuleMap() {
		Map<String, Module> moduleList = new HashMap<>();

		Set<Module> creationSet = new HashSet<>();
		Set<Module> structureSet = new HashSet<>();
		Set<Module> dataSet = new HashSet<>();
		creationSet.add(new CreationModuleCached());
		creationSet.add(new CreationModuleUncached());
		// new creation module add here

		for (Module creation : creationSet) {
			structureSet.add(new StructureModuleLinked(creation));
			structureSet.add(new StructureModuleFlat(creation));
			// new structure module add here

		}
		for (Module structure : structureSet) {
			dataSet.add(new DataModuleMap(structure));
			// new data module add here
		}

		for (Module data : dataSet) {
			moduleList.put(data.getName(), data);
		}

		return moduleList;
	}
	

}
