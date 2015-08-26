package cuz.psmm.factory.modules;

import cuz.psmm.factory.PsmmFactory;
import cuz.psmm.factory.data.Data;

public class DataModuleMap extends Module {

	public DataModuleMap(Module StructureModule) {
		super(StructureModule,"Map");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setup(PsmmFactory psmmFactory) {
		// TODO Auto-generated method stub

		psmmFactory.setData(Data.newData(Data.Type.MAP));
		this.collaberativeModule.setup(psmmFactory);
	}

}
