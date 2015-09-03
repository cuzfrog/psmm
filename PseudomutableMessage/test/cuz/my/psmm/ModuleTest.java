package cuz.my.psmm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import cuz.my.psmm.data.Data;

public class ModuleTest extends MyAbstractTest {

	CreationModuleCached cached = new CreationModuleCached();

	private final static int VALUE_PAIR_AMOUNT = 1000;

	@Before
	public void setUp() {
		initiate(6000);
		initiatePairList("int", 30, VALUE_PAIR_AMOUNT,Integer.MAX_VALUE);
		// prepare 1000 value pairs with 30 keys, but different values
	}

	@Test
	public void testCreateCachedMessage() {
		Set<TMessage<Integer>> messages = new HashSet<>();

		List<TMessage<Integer>> results = call(() -> {
			Data data = Data.newData(Data.Structure.MAP);
			Pair<?> pair = staticRandomPair();
			data.set(pair.getKey(), pair.getValue());
			TMessage<Integer> message = cached.createMessage(Messages.Style.CACHED_FLAT_MAP, RootMessage.getInstance(),
					data);
			return message;
		} , 500000, 6);

		messages.addAll(results);
		logger.info("Results size:{},Message size:{}", results.size(), messages.size());

		// see if those 1000 value pairs have been successfully conveyed by
		// these messages:
		List<Pair<?>> valuePairs = valuePairList();
		for (TMessage<Integer> message : messages) {
			for (Pair<?> pair : valuePairs) {
				if (pair.getValue().equals(message.get(pair.key))) {
					valuePairs.remove(pair);
					break;
				}
			}
		}
//		if (messages.size() > VALUE_PAIR_AMOUNT) {
//
//			for (TMessage<Integer> message : messages) {
//				logger.debug("message:{},signature:{}", message.getAll().toString(), message.getSignature());
//			}
//		}
		assertEquals(0, valuePairs.size()); // passed all info has been conveyed
		assertTrue(messages.size() <= VALUE_PAIR_AMOUNT); // sometimes failed,
															// but acceptable,
															// due to concurrent
															// issue.

	}

	@Ignore // passed
	@Test
	public void testCreateModuleMap() {
		Map<String, Module> modules = Module.createModuleMap();
		for (String key : modules.keySet()) {
			logger.debug("Key:{},class:{}", key, modules.get(key).getName());
		}
		logger.debug("size:{}", modules.size());
	}

}
