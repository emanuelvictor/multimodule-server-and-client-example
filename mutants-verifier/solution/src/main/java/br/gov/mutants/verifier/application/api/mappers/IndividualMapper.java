package br.gov.mutants.verifier.application.api.mappers;

import br.gov.mutants.verifier.commons.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.domain.entity.Individual;
import org.springframework.core.convert.converter.Converter;

public interface IndividualMapper extends Converter<IndividualDTO, Individual> {


}