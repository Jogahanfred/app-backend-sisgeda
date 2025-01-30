package pe.mil.fap.exception; 
 
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
 
import pe.mil.fap.dto.response.ResponseDTO; 
import pe.mil.fap.utils.enums.MessageTypeEnum;

import java.nio.file.AccessDeniedException;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

@RestControllerAdvice
@Order(1)
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ UnauthorizedException.class, 
						AccessDeniedException.class })
	@ResponseBody
	public void unauthorizedRequest() {
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	@ResponseBody
	public ResponseDTO notFoundRequest(Exception exception) {
		return ResponseDTO.createError(MessageTypeEnum.INFORMATION, exception, HttpStatus.NOT_FOUND.value());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestException.class, 
						DuplicateKeyException.class, 
						WebExchangeBindException.class,
						HttpMessageNotReadableException.class, 
						ServerWebInputException.class })
	@ResponseBody
	public ResponseDTO badRequest(Exception exception) {
		return ResponseDTO.createError(MessageTypeEnum.WARNING, exception, HttpStatus.BAD_REQUEST.value());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ ConflictException.class })
	@ResponseBody
	public ResponseDTO conflict(Exception exception) {
		return ResponseDTO.createError(MessageTypeEnum.WARNING, exception, HttpStatus.CONFLICT.value());
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ ForbiddenException.class })
	@ResponseBody
	public ResponseDTO forbidden(Exception exception) {
		return ResponseDTO.createError(MessageTypeEnum.WARNING, exception, HttpStatus.FORBIDDEN.value());
	}

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler({ BadGatewayException.class })
	@ResponseBody
	public ResponseDTO badGateway(Exception exception) {
		return ResponseDTO.createError(MessageTypeEnum.WARNING, exception, HttpStatus.BAD_GATEWAY.value());
	}

	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	@ExceptionHandler({ NotImplementedException.class })
	@ResponseBody
	public ResponseDTO notImplemented(Exception exception) {
		return ResponseDTO.createError(MessageTypeEnum.WARNING, exception, HttpStatus.NOT_IMPLEMENTED.value());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ResponseDTO exception(Exception exception) {
		exception.printStackTrace();
		return ResponseDTO.createError(MessageTypeEnum.DANGER, exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

}
