package br.gov.mutants.verifier.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMatrixException extends RuntimeException {

    /**
     *
     */
    public InvalidMatrixException() {
    }

    /**
     * @param message String
     */
    public InvalidMatrixException(final String message) {
        super(message);
    }
}
