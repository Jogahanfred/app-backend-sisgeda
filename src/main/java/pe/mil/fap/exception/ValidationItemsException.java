package pe.mil.fap.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pe.mil.fap.dto.response.ResponseDTO;
import pe.mil.fap.utils.enums.MessageTypeEnum;

@RestControllerAdvice
@Order(0)
public class ValidationItemsException {
 
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDTO handlerValidateExceptions(MethodArgumentNotValidException exception){
		Map<String, String> errors = new HashMap<String,String>();
		exception.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return ResponseDTO.createError(MessageTypeEnum.INFORMATION, errors, HttpStatus.BAD_REQUEST.value()); 
	}
}
 