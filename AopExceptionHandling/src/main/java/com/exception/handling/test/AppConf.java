package com.exception.handling.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.exception.handling.inftastructure.TechnicalExceptionHandler;

@Configuration
@ComponentScan(basePackageClasses = { TechnicalExceptionHandler.class,
		ExceptionThrower.class })
@EnableAspectJAutoProxy
public class AppConf {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				AppConf.class);
		ExceptionThrower myService = ctx.getBean(ExceptionThrower.class);
		myService.trowExeption();

	}

}
