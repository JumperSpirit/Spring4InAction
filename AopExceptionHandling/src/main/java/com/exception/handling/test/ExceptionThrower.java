package com.exception.handling.test;

import org.springframework.stereotype.Component;

import com.exception.handling.inftastructure.TechnicalException;

@Component
public class ExceptionThrower {

	@TechnicalException
	public void trowExeption() {
		int i = 10/0;
	}
}
