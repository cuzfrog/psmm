package com.github.cuzfrog.psmm;

import com.github.cuzfrog.psmm.Data;
import com.github.cuzfrog.psmm.PsmmFactory;

final class DataModuleMap extends Module {

	public DataModuleMap(Module StructureModule) {
		super(StructureModule, "Map");
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		super.setup(psmmFactory);
		psmmFactory.setData(Data.newData(Data.Structure.MAP));
		psmmFactory.setModule(this);
	}

}
