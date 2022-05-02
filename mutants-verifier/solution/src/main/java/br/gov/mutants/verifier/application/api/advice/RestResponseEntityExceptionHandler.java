package br.gov.mutants.verifier.application.api.advice;

import br.gov.mutants.verifier.application.exceptions.GenesCannotBeNullExceptionInvalidException;
import br.gov.mutants.verifier.application.exceptions.InvalidGeneException;
import br.gov.mutants.verifier.application.exceptions.InvalidMatrixException;
import br.gov.mutants.verifier.application.api.dtos.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class RestResponseEntityExceptionHandler
 *
 * @author Emanuel Victor
 * @version 0.0.1
 * @since 0.0.1, 11/03/2022
 */
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /*

     */
    @ExceptionHandler(GenesCannotBeNullExceptionInvalidException.class)
    public ResponseEntity<Object> handleException(final GenesCannotBeNullExceptionInvalidException exception, final WebRequest request) {
        return super.handleExceptionInternal(exception, new ErrorDTO(exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /*
     *
     */
    @ExceptionHandler(InvalidGeneException.class)
    public ResponseEntity<Object> handleException(final InvalidGeneException exception, final WebRequest request) {
        return super.handleExceptionInternal(exception, new ErrorDTO(exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /*
     *
     */
    @ExceptionHandler(InvalidMatrixException.class)
    public ResponseEntity<Object> handleException(final InvalidMatrixException exception, final WebRequest request) {
        return super.handleExceptionInternal(exception, new ErrorDTO(exception.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}