package br.gov.mutants.verifier.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenesCannotBeNullExceptionInvalidException extends InvalidGeneException {

    /**
     *
     */
    public GenesCannotBeNullExceptionInvalidException() {
    }

    /**
     * @param message String
     */
    public GenesCannotBeNullExceptionInvalidException(final String message) {
        super(message);
    }
}
