package cuz.psmm.test.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cuz.psmm.message.Message;

@Aspect
public class PsmmFactoryAop {
	Logger logger=LoggerFactory.getLogger("PsmmFactory");

	//define point:
	@Pointcut("execution(* cuz.psmm.PsmmFactory.set*(..))")
	public void allSet(){};
	@Pointcut("execution(* cuz.psmm.PsmmFactory.done(..))")
	public void done(){};


	//intercept behaviors：
	
	@After("allSet() && args(valueName,datum)")
	public void logSetArguments(String valueName,Object datum){
        logger.debug("Value Name:{},datum:{}",valueName,datum);
    }

	@AfterReturning(pointcut="done()" ,returning="result")
	public void checkDoneReturn(Message<?> result){
        logger.debug("Message Name:{},depth:{}",result,result.depth());
    }
}
