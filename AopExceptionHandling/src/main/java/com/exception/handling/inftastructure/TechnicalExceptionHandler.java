package com.exception.handling.inftastructure;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TechnicalExceptionHandler implements ThrowsAdvice {

	private static final Logger logger = LoggerFactory.getLogger(TechnicalExceptionHandler.class);
	
	@AfterThrowing(pointcut = "@annotation(TechnicalException)", throwing = "excep")
	public void afterThrowing(JoinPoint joinPoint, Throwable excep) {
		logger.error(excep.getMessage());
	}

}
