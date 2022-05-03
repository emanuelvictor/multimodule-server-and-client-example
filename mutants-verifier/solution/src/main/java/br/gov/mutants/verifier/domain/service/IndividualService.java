package br.gov.mutants.verifier.domain.service;

import br.gov.mutants.verifier.commons.v1.dto.StatsDTO;
import br.gov.mutants.verifier.domain.logics.business.IBusinessLogic;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.validation.IValidation;
import br.gov.mutants.verifier.domain.repository.IndividualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualService {

    /**
     *
     */
    private final IndividualRepository individualRepository;

    /**
     *
     */
    private final List<IBusinessLogic<Individual>> businessLogics;

    /**
     *
     */
    private final List<IValidation<Individual>> individualValidations;

    /**
     * @param individual Individual
     * @return Individual
     */
    public Individual save(final Individual individual) {

        individualValidations.forEach(individualIValidation -> individualIValidation.perform(individual));

        businessLogics.forEach(businessLogic -> businessLogic.perform(individual));

        return individualRepository.save(individual);
    }

    /**
     * @return StatusDTO
     */
    public StatsDTO stats() {
        return individualRepository.stats();
    }
}
