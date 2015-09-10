package cuz.my.psmm;

import java.util.Map;

import cuz.my.psmm.Messages.Style;

abstract class AbstractFactoryPool implements FactoryPool {

	protected final Map<String, Module> modules=Module.createModuleMap();  //read only after created.
	
	AbstractFactoryPool(PsmmConfiguration config) {

		// modules.putAll(config.getCustomModules());
				// because I want factory invisible from outside,it doesn't support
				// custom module for now.
	}

	@Override
	public PsmmFactory seekFactory(Style type) {
		PsmmFactory psmmFactory=createOrFetch();
		String name = type.getName();
		psmmFactory.assemble(modules.get(name), type);
		return psmmFactory;
	}
	
	protected abstract PsmmFactory createOrFetch();
}
