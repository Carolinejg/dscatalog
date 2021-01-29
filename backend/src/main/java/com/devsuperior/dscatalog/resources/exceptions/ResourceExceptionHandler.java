package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice //permite que a classe intercepte alguma exceção
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)  //para saber o tipo de exceção que ele vai interceptar precisar colocar os parâmetros 
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());//pega o horario atual do erro
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMessage(e.getMessage());//pega a mensagem de exceção do service
		error.setPath(request.getRequestURI());//pega o caminho da requisição
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	

}