package br.gov.mutants.verifier.commons.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO to Individual, it contains the genetic matrix in {@link IndividualDTO#dna} field.
 *
 * @author  Emanuel Victor
 * @version 1.0
 * @since   2022-04-29
 */
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDTO implements Serializable {

    /**
     * Genetic matrix from {@link IndividualDTO}.
     *
     * The genetic matrix have an array of {@link String}.
     * Wicth string only can contain 'ACTG' characters.
     * If the genetic characters blá blá blá blá blá ......
     *
     *
     */
    @Setter
    @Getter
    private String[] dna;

}
