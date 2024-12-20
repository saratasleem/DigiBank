package com.example.DigitalBankingApp.Utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.DigitalBankingApp.Exception.DigitalBankingException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Log LOGGER=LogFactory.getLog(ExceptionControllerAdvice.class);
	@Autowired
	private Environment env;
	
	//custom exception handler with bad request
	@ExceptionHandler(DigitalBankingException.class)
	public ResponseEntity<ErrorInfo> digibankException(DigitalBankingException digiException){
		LOGGER.error(digiException.getMessage(), digiException);
		ErrorInfo errInfo=new ErrorInfo();
		errInfo.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errInfo.setErrorMessage(env.getProperty(digiException.getMessage()));
		errInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errInfo,HttpStatus.BAD_REQUEST);
	}
	
	//general exception handler with internal server error
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exc){
		LOGGER.error(exc.getMessage(), exc);
		ErrorInfo errorinfo=new ErrorInfo();
		errorinfo.setErrorMessage(env.getProperty(ExceptionConstants.SERVER_ERROR.toString()));
		errorinfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorinfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorinfo,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//methodargument and constraintviolation exception handler
	@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> methConsExceptionHandler(Exception ex){
		LOGGER.error(ex.getMessage(), ex);
		String errMsg;
		if(ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException mnv=(MethodArgumentNotValidException)ex;
			errMsg=mnv.getBindingResult().getAllErrors()
					.stream().map(x->x.getDefaultMessage())
					.collect(Collectors.joining(", "));
		} else {
			ConstraintViolationException cve=(ConstraintViolationException)ex;
			errMsg=cve.getConstraintViolations()
					.stream().map(x->x.getMessage())
					.collect(Collectors.joining(", "));
		}
		ErrorInfo errinfo=new ErrorInfo();
		errinfo.setErrorMessage(errMsg);
		errinfo.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errinfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errinfo,HttpStatus.BAD_REQUEST);
	}
	
	

}
