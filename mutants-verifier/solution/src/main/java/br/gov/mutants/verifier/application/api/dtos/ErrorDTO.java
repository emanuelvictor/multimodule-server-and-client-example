package br.gov.mutants.verifier.application.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErrorDTO {

    /**
     *
     */
    @Getter
    private final String message;

}