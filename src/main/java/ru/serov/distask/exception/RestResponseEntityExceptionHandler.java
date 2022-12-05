package ru.serov.distask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.serov.distask.exception.impl.*;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {NameNotUniqueException.class})
    public String handleException(NameNotUniqueException ex) {
        return "This name: `" + ex.getMessage() + "` already exists.";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {HasAttachedArticlesException.class})
    public String handleException(HasAttachedArticlesException ex) {
        return "The product with id `" + ex.getMessage() + "` has attached articles." +
                " Please remove them before remove product.";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {ProductsHaveAttachedArticlesException.class})
    public String handleException(ProductsHaveAttachedArticlesException ex) {
        return "Products have attached articles. Please remove them before remove product.";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public String handleException(IllegalArgumentException ex) {
        return "Incorrect data format.";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityForPatchNotFoundException.class})
    public String handleException(EntityForPatchNotFoundException ex) {
        return "Entity for patch not found.";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityForUpdateNotFoundException.class})
    public String handleException(EntityForUpdateNotFoundException ex) {
        return "Entity for update not found.";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {AttachedProductNotFoundException.class})
    public String handleException(AttachedProductNotFoundException ex) {
        return "Attached product with id `" + ex.getMessage() + "` not found";
    }
}