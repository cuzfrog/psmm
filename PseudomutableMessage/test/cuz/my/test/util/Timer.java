package cuz.my.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Timer {
	private Logger logger;
	private long beginTime;
	public Timer(String name) {
		super();
		this.beginTime = System.currentTimeMillis();
		logger = LoggerFactory.getLogger(name);
	}
	
	public void stop(){
		logger.info("recorded {}ms.",System.currentTimeMillis()-beginTime);
	}
}
