package com.github.cuzfrog.psmm;

import java.util.Map;

import com.github.cuzfrog.psmm.PsmmConfiguration;
import com.github.cuzfrog.psmm.Messages.Style;

abstract class AbstractFactoryPool implements FactoryPool {

	protected final Map<Style, Module> modules=Module.createModuleMap();  //read only after created.
	
	AbstractFactoryPool(PsmmConfiguration config) {

		// modules.putAll(config.getCustomModules());
				// because I want factory invisible from outside,it doesn't support
				// custom module for now.
	}

	@Override
	public PsmmFactory seekFactory(Style type) {
		PsmmFactory psmmFactory=createOrFetch();
		psmmFactory.assemble(modules.get(type), type);
		return psmmFactory;
	}
	
	protected abstract PsmmFactory createOrFetch();
}
