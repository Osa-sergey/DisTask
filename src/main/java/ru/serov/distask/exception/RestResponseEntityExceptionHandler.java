package ru.serov.distask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.serov.distask.exception.impl.HasAttachedArticlesException;
import ru.serov.distask.exception.impl.NameNotUniqueException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {NameNotUniqueException.class})
    public String handleNameConflict(NameNotUniqueException ex) {
        return "This name: `" + ex.getMessage() + "` already exists. ";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {HasAttachedArticlesException.class})
    public String handelAttachedArticles(HasAttachedArticlesException ex) {
        return "The product with id `" + ex.getMessage() + "` has attached articles." +
                " Please remove them before remove product.";
    }
}
