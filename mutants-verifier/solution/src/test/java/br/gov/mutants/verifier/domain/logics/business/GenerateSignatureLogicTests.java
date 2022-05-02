package br.gov.mutants.verifier.domain.logics.business;

import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import br.gov.mutants.verifier.application.api.dtos.IndividualDTO;
import br.gov.mutants.verifier.application.api.mappers.IndividualMapper;
import br.gov.mutants.verifier.application.api.mappers.IndividualMapperImpl;
import br.gov.mutants.verifier.domain.logics.business.individual.GenerateSignatureLogic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class GenerateSignatureLogicTests {

    /**
     *
     */
    private final IndividualMapper individualMapper = new IndividualMapperImpl();

    /**
     *
     */
    private final IBusinessLogic<Individual> businessLogic = new GenerateSignatureLogic();

    /**
     *
     */
    @Test
    public void generateSignatureAndMutantTrueFromIndividualTest() {
        final Individual individual = individualMapper.convert(new IndividualDTO(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT));

        Assertions.assertThat(individual).isNotNull();

        businessLogic.perform(individual);

        Assertions.assertThat(String.join(Individual.SIGNATURE_SEPARATOR, GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT).equals(individual.getSignature())).isTrue();
    }

    /**
     *
     */
    @Test
    public void generateSignatureAndMutantFalseFromIndividualTest() {
        final Individual individual = individualMapper.convert(new IndividualDTO(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT));

        Assertions.assertThat(individual).isNotNull();

        businessLogic.perform(individual);

        Assertions.assertThat(String.join(Individual.SIGNATURE_SEPARATOR, GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT).equals(individual.getSignature())).isTrue();
    }

}
