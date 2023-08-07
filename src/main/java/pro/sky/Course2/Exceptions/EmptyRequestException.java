package pro.sky.Course2.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class EmptyRequestException extends RuntimeException{

    public EmptyRequestException(){
        super("Вы вели не верный запрос");

    }
}
