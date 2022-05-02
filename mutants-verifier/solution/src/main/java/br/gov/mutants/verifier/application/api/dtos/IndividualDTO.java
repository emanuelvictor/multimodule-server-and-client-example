package br.gov.mutants.verifier.application.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class IndividualDTO {

    @Setter
    @Getter
    private String[] dna;

}
