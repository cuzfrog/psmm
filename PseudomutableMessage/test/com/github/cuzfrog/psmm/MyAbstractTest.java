package com.github.cuzfrog.psmm;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.cuzfrog.psmm.PsmmConfiguration;
import com.github.cuzfrog.psmm.PsmmSystem;
import com.github.cuzfrog.psmm.Messages.Style;

public abstract class MyAbstractTest {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	protected ExecutorService executors = Executors.newFixedThreadPool(6);
	protected Style[] types = com.github.cuzfrog.psmm.Messages.Style.values();
	
	

	protected static List<Pair<?>> valuePairs;
	protected static List<String> names;

	public Style randomType() {
		return types[ThreadLocalRandom.current().nextInt(types.length)];
	}

	public static Pair<?> staticRandomPair() {
		return valuePairs.get(ThreadLocalRandom.current().nextInt(valuePairs.size()));
	}

	

	public List<Pair<?>> valuePairList() {
		return new CopyOnWriteArrayList<>(valuePairs);
	}

	public static List<String> nameList() {
		return new CopyOnWriteArrayList<>(names);
	}

	protected static List<String> keyList(String name, int amount) {
		List<String> keys = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		for (int i = 0; i < amount; i++) {
			String num = Integer.valueOf(i).toString();
			sb.replace(name.length(), name.length() + num.length(), num);
			keys.add(sb.toString());
		}
		return keys;
	}

	protected static void initiateNameList(String name, int amount) {
		names = Collections.unmodifiableList(keyList(name, amount));
	}

	public static List<Pair<?>> initiatePairList(String keyName, int keyAmount, int pairsAmount,int randomBound) {
		List<Pair<?>> valuePairs = new ArrayList<>();
		List<String> keys = keyList(keyName, keyAmount);
		int i = 0;
		Pair<?> pair = null;
		while (i < pairsAmount) {
			if (keyName.contains("int")) {
				pair = new Pair<Integer>(keys.get(ThreadLocalRandom.current().nextInt(keys.size())),
						ThreadLocalRandom.current().nextInt(randomBound));
			}else if(keyName.contains("double")){
				pair = new Pair<Double>(keys.get(ThreadLocalRandom.current().nextInt(keys.size())),
						ThreadLocalRandom.current().nextDouble(randomBound));
			}else{
				pair = new Pair<String>(keys.get(ThreadLocalRandom.current().nextInt(keys.size())),
						UUID.randomUUID().toString().substring(0, randomBound));
			}
			if (!valuePairs.contains(pair)) {
				valuePairs.add(pair);
				i++;
			}
		}

		MyAbstractTest.valuePairs = Collections.unmodifiableList(valuePairs);
		return MyAbstractTest.valuePairs;
	}

	/**
	 * initiate PsmmSystem.
	 */
	public static void initiate(int messagePoolSize) {
		PsmmSystem.initiate(new PsmmConfiguration());

	}

	/**
	 * Concurrently run task.
	 * 
	 * @param task
	 * @param executeTimes
	 *            how many times of invoking the task.
	 * @param threadCnt
	 */
	protected void run(Runnable task, long executeTimes, int threadCnt) {
		ExecutorService executor = Executors.newFixedThreadPool(threadCnt);
		for (int i = 0; i < executeTimes; i++) {
			executor.execute(task);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.warn("Await interrupted,executor id:{}", executor);
		}
	}

	protected void run(Runnable task) {
		this.run(task, 5000000, 6);
	}

	/**
	 * Concurrently run task and return a list of results.
	 * 
	 * @param task
	 * @param executeTimes
	 *            how many times of invoking the task.
	 * @param threadCnt
	 * @return List of results.
	 */
	protected <T> List<T> call(Callable<T> task, long executeTimes, int threadCnt) {
		ExecutorService executor = Executors.newFixedThreadPool(threadCnt);
		List<Future<T>> futures = new ArrayList<>();
		for (int i = 0; i < executeTimes; i++) {
			futures.add(executor.submit(task));
		}
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.warn("Await interrupted,executor id:{}", executor);
		}
		List<T> results = new ArrayList<>(futures.size());
		for (Future<T> future : futures) {
			try {
				results.add(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				fail(e.toString());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				fail(e.toString());
			}
		}

		return results;

	}

}
