package br.gov.mutants.verifier.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidGeneException extends RuntimeException {

    /**
     *
     */
    public InvalidGeneException() {
    }

    /**
     * @param message String
     */
    public InvalidGeneException(final String message) {
        super(message);
    }
}
