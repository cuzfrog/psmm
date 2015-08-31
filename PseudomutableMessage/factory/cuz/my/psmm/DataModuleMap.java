package cuz.my.psmm;

import cuz.my.psmm.PsmmFactory;
import cuz.my.psmm.data.Data;

final class DataModuleMap extends Module {

	public DataModuleMap(Module StructureModule) {
		super(StructureModule, "Map");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		super.setup(psmmFactory);
		// TODO Auto-generated method stub
		psmmFactory.setData(Data.newData(Data.Structure.MAP));

	}

}
