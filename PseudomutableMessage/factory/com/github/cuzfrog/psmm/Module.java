package com.github.cuzfrog.psmm;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.cuzfrog.psmm.Messages.Style;
import com.github.cuzfrog.psmm.exceptions.PsmmFactoryModuleChainErrorException;

/**
 *
 * @author Cause Chung
 *
 */
abstract class Module {

	private final Module collaberativeModule;
	private final String name;
	private final Messages.Style style;

	protected Module(Module collaberativeModule, String name) {
		this.collaberativeModule = collaberativeModule;
		if (collaberativeModule != null) {
			this.name = collaberativeModule.getName().concat(name);
		} else {
			this.name = name;
		}
		this.style = Style.fromString(this.name); //only data module has style
	}

	/**
	 * Method for factory to invoke. Create new concrete message depending on
	 * specific module.
	 * 
	 * @param type
	 * @param messageBeingWrapped
	 * @param data
	 * @return new concrete message
	 */
	protected <K,T> Message<K,T> createMessage(Messages.Style type, Message<K,T> messageBeingWrapped, Data data) {
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

	Style getStyle() {
		return this.style;
	}

	Module getCollaberativeModule() {
		return collaberativeModule;
	}

	// static methods:
	static synchronized Map<Style, Module> createModuleMap() {
		Map<Style, Module> moduleList = new EnumMap<>(Style.class);

		Set<Module> creationSet = new HashSet<>();
		Set<Module> structureSet = new HashSet<>();
		Set<Module> dataSet = new HashSet<>();
		// creationSet.add(new CreationModuleRetained());
		creationSet.add(new CreationModule("Unique"));
		creationSet.add(new CreationModule("Value"));
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

		// only data set is as the outer module
		for (Module data : dataSet) {
			moduleList.put(data.getStyle(), data);
		}

		return moduleList;
	}

}
