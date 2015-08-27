package cuz.my.psmm;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import cuz.my.psmm.Module;

class ModuleTest extends AbstractTest{

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testCreateMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateModuleMap() {
		Map<String,Module> modules=Module.createModuleMap();
		for(String key:modules.keySet()){
			logger.debug("Key:{},class:{}",key,modules.get(key).getName());
		}
		logger.debug("size:{}",modules.size());
	}

}
