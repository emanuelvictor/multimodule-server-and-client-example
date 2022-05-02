package br.gov.mutants.verifier.application.api.mappers;

import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.application.api.dtos.IndividualDTO;
import org.springframework.core.convert.converter.Converter;

public interface IndividualMapper extends Converter<IndividualDTO, Individual> {


}