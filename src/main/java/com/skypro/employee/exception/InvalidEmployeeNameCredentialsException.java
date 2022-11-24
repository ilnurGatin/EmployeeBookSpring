package com.skypro.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid data entered")
public class InvalidEmployeeNameCredentialsException extends RuntimeException{

}
