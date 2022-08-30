package br.gov.mutants.verifier.domain.logics.business;

import br.gov.mutants.verifier.application.api.mappers.IndividualMapper;
import br.gov.mutants.verifier.application.api.mappers.IndividualMapperImpl;
import br.gov.mutants.verifier.application.exceptions.InvalidMatrixException;
import br.gov.mutants.verifier.commons.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import br.gov.mutants.verifier.domain.logics.business.individual.SearchMutantGenesLogic;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class SearchMutantGenesLogicTests {

    /**
     *
     */
    private final IndividualMapper individualMapper = new IndividualMapperImpl();

    /**
     *
     */
    private final IBusinessLogic<Individual> businessLogic = new SearchMutantGenesLogic();

    /**
     *
     */
    @Test
    public void generateSignatureAndMutantTrueFromIndividualTest() {
        final Individual individual = individualMapper.convert(new IndividualDTO(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT));

        Assertions.assertThat(individual).isNotNull();

        businessLogic.perform(individual);

        Assertions.assertThat(individual.isMutant()).isTrue();
    }

    /**
     *
     */
    @Test
    public void generateSignatureAndMutantFalseFromIndividualTest() {
        final Individual individual = individualMapper.convert(new IndividualDTO(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT));

        Assertions.assertThat(individual).isNotNull();

        businessLogic.perform(individual);

        Assertions.assertThat(individual.isMutant()).isFalse();
    }

    /**
     *
     */
    @Test
    public void verifyIfGeneIsMutant() {
        final String humanGene = "ACGTTT";
        Assertions.assertThat(SearchMutantGenesLogic.isMutant(humanGene)).isFalse();
        final String mutantGene = "ACTTTT";
        Assertions.assertThat(SearchMutantGenesLogic.isMutant(mutantGene)).isTrue();
    }


    /**
     *
     */
    @Test
    public void extractionVerticalAndHorizontalGenesFromInvalidGeneticMatrixTest() {
        Assertions.assertThatThrownBy(() -> SearchMutantGenesLogic.extractVerticalAndHorizontalGenes(null)).isInstanceOf(InvalidMatrixException.class);

        final String[] geneticMatrix = new String[]{};
        Assertions.assertThatThrownBy(() -> SearchMutantGenesLogic.extractVerticalAndHorizontalGenes(geneticMatrix)).isInstanceOf(InvalidMatrixException.class);
    }

    /**
     *
     */
    @Test
    public void extractionDiagonalGenesFromInvalidGeneticMatrixTest() {
        Assertions.assertThatThrownBy(() -> SearchMutantGenesLogic.extractDiagonalGenes(null)).isInstanceOf(InvalidMatrixException.class);

        final String[] geneticMatrix = new String[]{};
        Assertions.assertThatThrownBy(() -> SearchMutantGenesLogic.extractDiagonalGenes(geneticMatrix)).isInstanceOf(InvalidMatrixException.class);
    }

    /**
     *
     */
    @Test
    public void extractionVerticalAndHorizontalMutantGenesFromStaticGeneticMatrixWithLength6Test() {
        String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractVerticalAndHorizontalGenes(geneticMatrix)).isTrue();

        geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_HORIZONTAL_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractVerticalAndHorizontalGenes(geneticMatrix)).isTrue();
    }

    /**
     *
     */
    @Test
    public void extractionVerticalAndHorizontalGenesFromStaticGeneticMatrixWithLength6Test() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractVerticalAndHorizontalGenes(geneticMatrix)).isFalse();
    }

    /**
     *
     */
    @Test
    public void extractAllDiagonalGenesFromGeneticMatrixWithLength6Test() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractDiagonalGenes(geneticMatrix)).isFalse();
    }

    /**
     *
     */
    @Test
    public void extractAllDiagonalGenesFromMutantGeneticMatrixWithLength6Test() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractDiagonalGenes(geneticMatrix)).isTrue();
    }

    /**
     *
     */
    @Test
    public void extractAllGenesFromGeneticMatrixWithLength6Test() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractAllGenes(geneticMatrix)).isFalse();
    }

    /**
     *
     */
    @Test
    public void extractAllGenesFromGeneticMatrixWithLength9Test() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_9_WITH_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractAllGenes(geneticMatrix)).isTrue();
    }

    /**
     *
     */
    @Test
    public void extractAllGenesFromGeneticMatrixWithLength10Test() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_10_WITH_MUTANT;
        Assertions.assertThat(SearchMutantGenesLogic.extractAllGenes(geneticMatrix)).isTrue();
    }
}
