package com.brixo.sytem.creditmanagement.contoller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.brixo.sytem.creditmanagement.exeception.ApplicationsNotFoundException;

public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ApplicationsNotFoundException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
