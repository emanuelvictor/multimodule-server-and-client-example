package br.gov.mutants.verifier.commons.v1;


import br.gov.mutants.verifier.commons.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.commons.v1.dto.StatsDTO;

/**
 * @author Emanuel Victor
 * @version 1.0
 * @since 2022-04-29
 */
public interface IIndividualClient {

    /**
     * Return the statistcs of the individuals.
     *
     * @return {@link StatsDTO}
     */
    StatsDTO stats();

    /**
     * Endpoint to verify if the {@link IndividualDTO} with genetic matrix, is a mutant or normal human.
     *
     * @param individualDTO {@link IndividualDTO}
     * @return {@link Boolean}
     */
    Boolean mutant(final IndividualDTO individualDTO);

}