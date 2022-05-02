package br.gov.mutants.verifier.domain.logics.validation;

import br.gov.mutants.verifier.application.exceptions.GenesCannotBeNullExceptionInvalidException;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.validation.individual.NotNullGenesValidation;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class NotNullGenesValidationTests {

    /**
     *
     */
    @Autowired
    private NotNullGenesValidation validation;

    /**
     *
     */
    @Test
    public void validateInstanceOfExceptionTest() {
        final GenesCannotBeNullExceptionInvalidException exception = new GenesCannotBeNullExceptionInvalidException();
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getMessage()).isNull();
    }

    /**
     *
     */
    @Test
    public void nonNullGenesValidationTest() {

        final Individual individual = new Individual();
        individual.setGenes(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT);

        validation.perform(individual);

        Assertions.assertThat(individual.getGenes()).isNotNull();
    }

    /**
     *
     */
    @Test
    public void withAllNullGenesValidationTest() {
        final Individual individual = new Individual();
        Assertions.assertThatThrownBy(() -> validation.perform(individual)).isInstanceOf(GenesCannotBeNullExceptionInvalidException.class);
    }

    /**
     *
     */
    @Test
    public void withOneNullGeneValidationTest() {

        final String[] genes = new String[GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length];
        System.arraycopy(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT, 0, genes, 0, GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length);

        genes[genes.length - 1] = null;

        final Individual individual = new Individual();
        individual.setGenes(genes);

        Assertions.assertThatThrownBy(() -> validation.perform(individual)).isInstanceOf(GenesCannotBeNullExceptionInvalidException.class);
    }
}
