package com.brixo.sytem.creditmanagement.exeception;

public class ApplicationsNotFoundException extends RuntimeException{
	public ApplicationsNotFoundException(String message, Throwable cause) {
        super(message, cause );
    }
}
