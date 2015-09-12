package cuz.my.psmm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cuz.my.psmm.exceptions.PsmmFactoryModuleChainErrorException;

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

	/**
	 * Method for factory to invoke. Create new concrete message depending on specific module.
	 * 
	 * @param type
	 * @param messageBeingWrapped
	 * @param data
	 * @return new concrete message
	 */
	protected <T> Message<T> createMessage(Messages.Style type, Message<T> messageBeingWrapped, Data data) {
		if (collaberativeModule != null) {
			return collaberativeModule.createMessage(type, messageBeingWrapped, data);
		}
		throw new PsmmFactoryModuleChainErrorException();
	}

	/**
	 * Method for factory to invoke. Setup factory module and data.
	 * 
	 * @param psmmFactory
	 */
	protected void setup(PsmmFactory psmmFactory) {
		if (collaberativeModule != null) {
			collaberativeModule.setup(psmmFactory);
		}
	}

	String getName() {
		return this.name;
	}

	Module getCollaberativeModule() {
		return collaberativeModule;
	}

	// static methods:
	// there's still room for optimization: change Map to Array, use predefined
	// order to find module.
	static Map<String, Module> createModuleMap() {
		Map<String, Module> moduleList = new HashMap<>();

		Set<Module> creationSet = new HashSet<>();
		Set<Module> structureSet = new HashSet<>();
		Set<Module> dataSet = new HashSet<>();
		//creationSet.add(new CreationModuleRetained());
		creationSet.add(new CreationModuleFree());
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
