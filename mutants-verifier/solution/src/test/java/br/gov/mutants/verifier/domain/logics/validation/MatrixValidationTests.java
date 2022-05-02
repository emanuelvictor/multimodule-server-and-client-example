package br.gov.mutants.verifier.domain.logics.validation;

import br.gov.mutants.verifier.application.exceptions.InvalidMatrixException;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import br.gov.mutants.verifier.domain.logics.validation.individual.MatrixValidation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class MatrixValidationTests {

    /**
     *
     */
    @Autowired
    private MatrixValidation validation;

    /**
     *
     */
    @Test
    public void validMatrixTest() {
        final Individual individual = new Individual();
        individual.setGenes(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT);

        validation.perform(individual);

        Assertions.assertThat(individual.getGenes()).isNotNull();
    }

    /**
     *
     */
    @Test
    public void invalidMatrixTest() {
        final String[] genes = new String[GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length];
        System.arraycopy(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT, 0, genes, 0, GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length);

        genes[genes.length - 1] = genes[genes.length - 1] + "A";

        final Individual individual = new Individual();
        individual.setGenes(genes);

        Assertions.assertThatThrownBy(() -> validation.perform(individual)).isInstanceOf(InvalidMatrixException.class);
    }

    /**
     *
     */
    @Test
    public void instanceOfExceptionTest() {
        final InvalidMatrixException invalidMatrixException = new InvalidMatrixException();
        Assertions.assertThat(invalidMatrixException).isNotNull();
        Assertions.assertThat(invalidMatrixException.getMessage()).isNull();
    }
}
