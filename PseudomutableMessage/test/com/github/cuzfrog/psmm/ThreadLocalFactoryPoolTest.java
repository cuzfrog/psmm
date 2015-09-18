package com.github.cuzfrog.psmm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ThreadLocalFactoryPoolTest {

	private static final int THREAD_CNT=16;
	
	
	private static FactoryPool pool;
	private static ExecutorService executor = Executors.newFixedThreadPool(THREAD_CNT);

	private class Task implements Callable<PsmmFactory> {
		@Override
		public PsmmFactory call() throws Exception {
			return pool.seekFactory(Messages.Style.LINKED_MAP);
		}

	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PsmmConfiguration config = new PsmmConfiguration();
		pool = new ThreadLocalFactoryPool(config);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws InterruptedException, ExecutionException {
		List<Future<PsmmFactory>> list = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			list.add(executor.submit(new Task()));
		}
		Set<PsmmFactory> set=new HashSet<>();
		for (Future<PsmmFactory> future : list) {
			set.add(future.get());
		}

		assertEquals(set.size(),THREAD_CNT);
	}

}
