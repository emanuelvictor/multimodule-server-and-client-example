package br.gov.mutants.verifier.application.api.mappers;

import br.gov.mutants.verifier.client.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.domain.entity.Individual;
import org.springframework.stereotype.Component;

@Component
public class IndividualMapperImpl implements IndividualMapper {

    /**
     * @param individualDTO IndividualDTO
     * @return Individual
     */
    @Override
    public Individual convert(final IndividualDTO individualDTO) {

        final Individual individual = new Individual();

        individual.setGenes(individualDTO.getDna());

        return individual;
    }




}