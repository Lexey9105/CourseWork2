package pro.sky.Course2.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.METHOD_NOT_ALLOWED)
public class AddException extends RuntimeException{
}
