package com.excilys.formation.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.excilys.formation.webservice.impl.ThumbnailWebServiceImpl.BadParamException;

@ControllerAdvice
public class ExceptionHandlingController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(BadParamException.class)
    public void handleConflict() {
        // Nothing to do
    	System.out.println("bad request");
    }
	
}
