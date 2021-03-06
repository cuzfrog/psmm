package com.github.cuzfrog.psmm;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.cuzfrog.psmm.Messages.Style;

public class ValueMessageTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PsmmSystem.initiate();
	}

	@SuppressWarnings("boxing")
	@Test
	public void test() {
		UMessage m1 = Messages.builder(Style.VALUE_FLAT_MAP).set("int1", 3).set("int2", 12).build();
		assertTrue(m1.equals(m1)); // Reflective
		printHashCode(m1, 1);
		UMessage m2 = Messages.builder(Style.VALUE_LINKED_MAP).set("int1", 3).set("int2", 12).build();
		printHashCode(m2, 2);
		TBuilder<String, Integer> b3 = Messages.typedBuilder(Style.VALUE_FLAT_MAP);
		TMessage<String, Integer> m3 = b3.set("int1", 3).set("int2", 12).build();
		printHashCode(m3, 3);
		TBuilder<String, Integer> b4=Messages.typedBuilder(Style.VALUE_LINKED_MAP);
		TMessage<String,Integer> m4 = b4.set("int1", 3).set("int2", 12).build();
		printHashCode(m4, 4);
		
		Map<Integer, Object> ms = new HashMap<>();
		ms.put(1, m1);
		ms.put(2, m2);
		ms.put(3, m3);
		ms.put(4, m4);

		equalTest(ms);

		UMessage m5 = m1.set("int3", 785).set("int4", 6).build();
		printHashCode(m5, 5);
		UMessage m6 = m2.set("int3", 785).set("int4", 6).build();
		printHashCode(m6, 6);
		TMessage<String,Integer> m7 = m3.set("int3", 785).set("int4", 6).build();
		printHashCode(m7, 7);
		TMessage<String,Integer> m8 = m4.set("int3", 785).set("int4", 6).build();
		printHashCode(m8, 8);

		Map<Integer, Object> ms2 = new HashMap<>();
		ms2.put(5, m5);
		ms2.put(6, m6);
		ms2.put(7, m7);
		ms2.put(8, m8);

		equalTest(ms2);

	}

	private static <T> void equalTest(Map<T, Object> ms) {
		for (T ml : ms.keySet()) {
			for (T mr : ms.keySet()) {
				System.out.println(ml + " equals " + mr);
				if (!ms.get(ml).equals(ms.get(mr))) {
					fail("ml:" + ms.get(ml) + ";   " + "mr:" + ms.get(mr));
					// Symmetry, Reflective and Transitivity
					// Since message is immutable, they should be Consistent.
				}
			}
			assertFalse(ml.equals(null)); // when meeting null, return false
		}
	}

	private static void printHashCode(Object m, Integer num) {
		if (m instanceof Message) {
			Message<?,?> mnew = (Message<?,?>) m;
			System.out.println(num + " hashCode:" + mnew.hashCode() + ";  map's hashCode:" + mnew.getAll().hashCode());
		}
	}
}
