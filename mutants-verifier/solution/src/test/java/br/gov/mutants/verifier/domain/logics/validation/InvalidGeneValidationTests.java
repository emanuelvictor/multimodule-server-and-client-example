package br.gov.mutants.verifier.domain.logics.validation;

import br.gov.mutants.verifier.application.exceptions.InvalidGeneException;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import br.gov.mutants.verifier.domain.logics.validation.individual.InvalidGeneValidation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class InvalidGeneValidationTests {

    /**
     *
     */
    @Autowired
    private InvalidGeneValidation validation;

    /**
     *
     */
    @Test
    public void validateInstanceOfExceptionTest() {
        final InvalidGeneException exception = new InvalidGeneException();
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getMessage()).isNull();
    }

    /**
     *
     */
    @Test
    public void onlyValidGenesTest() {
        final Individual individual = new Individual();
        individual.setGenes(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT);

        validation.perform(individual);

        Assertions.assertThat(individual.getGenes()).isNotNull();
    }

    /**
     *
     */
    @Test
    public void oneGeneWithNumberTest() {

        final String[] genes = new String[GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length];
        System.arraycopy(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT, 0, genes, 0, GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length);

        genes[genes.length - 1] = "1GGTAC";

        final Individual individual = new Individual();
        individual.setGenes(genes);

        Assertions.assertThatThrownBy(() -> validation.perform(individual)).isInstanceOf(InvalidGeneException.class);
    }

}
